package com.example.dddhibernate.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Product {

  @Id
  @GeneratedValue
  Long id;

  String description;

  BigDecimal price;

  @ManyToMany
  List<Image> images;

  // Product is fetched from Order as well as retrieved directly.
  // In this case, you may want to lazily fetch associated entities
  @ManyToOne(fetch = FetchType.LAZY)
  Admin admin;

}
