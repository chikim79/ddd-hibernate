package com.example.dddhibernate.repository;

import com.example.dddhibernate.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CustomerRepository extends CrudRepository<Customer, Long> {

  Customer findByEmail(@Param("email") String email);

}
