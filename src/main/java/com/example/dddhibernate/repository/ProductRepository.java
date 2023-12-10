package com.example.dddhibernate.repository;

import com.example.dddhibernate.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Long> {



}
