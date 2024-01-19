package com.example.dddhibernate.dto;

import java.math.BigDecimal;
import lombok.Data;

// DTO for Order object
@Data
public class OrderDto {
  Long customerId;

  BigDecimal totalPrice;

}
