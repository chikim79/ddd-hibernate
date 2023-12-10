package com.example.dddhibernate.domain;

import jakarta.persistence.*;
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
                        attributeNodes = {
                                @NamedAttributeNode("product")
                        }
                )
        }
)
@Entity
@Data
@Table(name = "ORDERS")
public class Order {

  @Id
  @GeneratedValue
  Long id;

  Long customerId;

  BigDecimal totalPrice;

  // Eagerly Fetches by Default
  @ManyToOne
  Address shippingAddress;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
  List<OrderItem> orderItems;

}
