package com.example.dddhibernate.domain;

import com.example.dddhibernate.dto.OrderDto;
import jakarta.persistence.*;
import java.util.ArrayList;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@NamedEntityGraph(
    name = "order-join-shipping",
    attributeNodes = {
      @NamedAttributeNode("shippingAddress"),
      @NamedAttributeNode(value = "orderItems", subgraph = "orderItemProduct")
    },
    subgraphs = {
      @NamedSubgraph(
          name = "orderItemProduct",
          attributeNodes = {@NamedAttributeNode("product")})
    })
@Entity
@Data
@Table(name = "ORDERS")
public class Order {

  @Id @GeneratedValue Long id;

  Long customerId;

  BigDecimal totalPrice;

  // Eagerly Fetches by Default
  @ManyToOne Address shippingAddress;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
  List<OrderItem> orderItems = new ArrayList<>();

  // Derived Value
  public long getOrderItemsCount() {
    return orderItems.size();
  }

  // Derived Value
  public long getTotalQuantity() {
    return orderItems.stream().mapToLong(OrderItem::getQuantity).sum();
  }

  // State Mutation Method
  public void addProduct(Product product, long quantity) {
    orderItems.stream()
        .filter(orderItem -> orderItem.getProduct().equals(product))
        .findFirst()
        .ifPresentOrElse(
            orderItem -> orderItem.setQuantity(orderItem.getQuantity() + quantity),
            () -> addNewOrderItem(product, quantity));

    totalPrice = totalPrice.add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
  }

  // Private State Mutation Method
  private void addNewOrderItem(Product product, long quantity) {
    OrderItem orderItem = new OrderItem();
    orderItem.setProduct(product);
    orderItem.setQuantity(quantity);
    orderItem.setOrder(this);
    orderItems.add(orderItem);
  }

  private BigDecimal orderItemsTotalPrice() {
    return orderItems.stream()
        .map(
            orderItem ->
                orderItem
                    .getProduct()
                    .getPrice()
                    .multiply(BigDecimal.valueOf(orderItem.getQuantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  // Validation
  public void validate() {
    if (orderItems.isEmpty()) {
      throw new IllegalStateException("Order must have at least one item");
    }

    if (totalPrice.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalStateException("Order total price must be greater than zero");
    }

    if (totalPrice.compareTo(orderItemsTotalPrice()) != 0) {
      throw new IllegalStateException("Order total price must match sum of order items prices");
    }
  }

  // Create Method
  public static Order createQuickOrder(Customer customer, Product product) {
    Order order = new Order();
    order.setCustomerId(customer.getId());
    order.addProduct(product, 1);
    order.validate();
    return order;
  }

  // Create Method
  public static Order createOrderFromOrderItems(Customer customer, List<OrderItem> orderItems) {
    Order order = new Order();
    order.setCustomerId(customer.getId());
    order.setOrderItems(orderItems);
    order.setTotalPrice(order.orderItemsTotalPrice());
    order.validate();
    return order;
  }

  // Mapper Method
  public OrderDto toDto() {
    OrderDto orderDto = new OrderDto();
    orderDto.setCustomerId(customerId);
    orderDto.setTotalPrice(totalPrice);
    return orderDto;
  }
}
