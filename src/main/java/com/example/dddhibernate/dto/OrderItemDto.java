package com.example.dddhibernate.dto;

import com.example.dddhibernate.domain.Product;
import lombok.Data;

@Data
public class OrderItemDto {

  Long quantity;
  
  Long productId;

  
}
