package com.example.dddhibernate.controller;

import com.example.dddhibernate.domain.Order;
import com.example.dddhibernate.repository.CustomerRepository;
import com.example.dddhibernate.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

  final OrderRepository orderRepository;
  final CustomerRepository customerRepository;

  @GetMapping("/customer/order/{id}")
  public Long getOrder(@PathVariable("id") Long id) {
    Order byCustomerId = orderRepository.findByCustomerId(id);
    byCustomerId.getOrderItems().get(0).getProduct().getDescription();
    return 1L;
  }

  @GetMapping("/customer/order2/{id}")
  public Long getOrder2(@PathVariable("id") Long id) {
    Order byCustomerId = orderRepository.findByCustomerIdCustomQuery(id);
    byCustomerId.getOrderItems().get(0).getProduct().getDescription();
    return 1L;
  }

  @GetMapping("/customer/{id}")
  public Long getCustomer(@PathVariable("id") Long id) {
    customerRepository.findById(id).get();
    return 1L;
  }
}
