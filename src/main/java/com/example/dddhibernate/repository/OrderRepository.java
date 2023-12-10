package com.example.dddhibernate.repository;

import com.example.dddhibernate.domain.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface OrderRepository extends CrudRepository<Order, Long> {

  @EntityGraph("order-join-shipping")
  Order findByCustomerId(@Param("customerId") Long customerId);

  @Query("""
          select o from Order o 
          left join fetch o.orderItems oi
          left join fetch oi.product
          left join fetch o.shippingAddress
          where o.customerId = :customerId
          """)
  Order findByCustomerIdCustomQuery(@Param("customerId") Long customerId);

  // You cannot fetch associated entities in the same query from native query
  @Query(nativeQuery = true, value = """
          select o.* from Order o 
          where o.customerId = :customerId
          """)
  Order findByCustomerIdNativeQuery(@Param("customerId") Long customerId);

}
