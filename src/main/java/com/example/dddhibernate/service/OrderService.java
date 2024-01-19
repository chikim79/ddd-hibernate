package com.example.dddhibernate.service;

import com.example.dddhibernate.domain.Customer;
import com.example.dddhibernate.domain.Order;
import com.example.dddhibernate.domain.OrderItem;
import com.example.dddhibernate.domain.Product;
import com.example.dddhibernate.dto.OrderDto;
import com.example.dddhibernate.dto.OrderItemDto;
import com.example.dddhibernate.repository.OrderRepository;
import com.example.dddhibernate.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final ProductRepository productRepository;
  private final OrderRepository orderRepository;

  public OrderDto createNewOrder(Customer customer, List<OrderItemDto> orderItems) {

    Order order =
        Order.createOrderFromOrderItems(
            customer, orderItems.stream().map(this::createOrderItemFromDto).toList());

    orderRepository.save(order);

    return order.toDto();
  }

  private OrderItem createOrderItemFromDto(OrderItemDto orderItemDto) {
    Product product = productRepository.findById(orderItemDto.getProductId()).get();

    return OrderItem.fromDto(orderItemDto, product);
  }














  public OrderDto createNewOrderNonDDD(Customer customer, List<OrderItemDto> orderItems) {
    Order order = new Order();
    order.setCustomerId(customer.getId());

    List<OrderItem> orderItemsList =
        orderItems.stream()
            .map(
                orderItemDto -> {
                  Product product = productRepository.findById(orderItemDto.getProductId()).get();

                  OrderItem orderItem = new OrderItem();
                  orderItem.setProduct(product);
                  orderItem.setQuantity(orderItemDto.getQuantity());

                  return orderItem;
                })
            .toList();

    order.setOrderItems(orderItemsList);

    BigDecimal totalPrice =
        orderItemsList.stream()
            .map(
                orderItem ->
                    orderItem
                        .getProduct()
                        .getPrice()
                        .multiply(BigDecimal.valueOf(orderItem.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    order.setTotalPrice(totalPrice);

    orderRepository.save(order);

    OrderDto orderDto = new OrderDto();
    orderDto.setCustomerId(order.getCustomerId());
    orderDto.setTotalPrice(order.getTotalPrice());

    return orderDto;
  }
}
