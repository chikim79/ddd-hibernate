package com.example.dddhibernate;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Customer {

  @Id
  @GeneratedValue
  Long id;

  String email;

  String password;

  @OneToOne
  @JoinColumn(name = "home_address_id")
  Address homeAddress;
}
