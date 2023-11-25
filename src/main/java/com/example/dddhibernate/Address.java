package com.example.dddhibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Address {
  @Id
  @GeneratedValue
  Long id;

  String street;

  String city;
}
