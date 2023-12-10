package com.example.dddhibernate.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Image {

  @Id
  @GeneratedValue
  Long id;

  String url;



}
