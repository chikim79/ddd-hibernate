package com.example.dddhibernate.domain;

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

  // Usually it is preferred to not create OneToOne relationship
  // in favor of using a single table with columns from both object.
  // In this case, it makes sense to use a separate table since Address object is also referenced
  // from other entities (Order)
  @OneToOne
  @JoinColumn(name = "home_address_id")
  Address homeAddress;
}
