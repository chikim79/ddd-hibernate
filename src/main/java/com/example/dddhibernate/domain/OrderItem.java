package com.example.dddhibernate.domain;

import com.example.dddhibernate.dto.OrderItemDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class OrderItem {

  @Id
  @GeneratedValue
  Long id;

  Long quantity;

  // eager by default
  @ManyToOne
  Order order;

  // eager by default
  @ManyToOne
  Product product;

  public static OrderItem fromDto(OrderItemDto orderItemDto, Product product) {
    OrderItem orderItem = new OrderItem();
    orderItem.setQuantity(orderItemDto.getQuantity());
    orderItem.setProduct(product);
    return orderItem;
  }

}
