package com.example.dddhibernate.repository;

import com.example.dddhibernate.domain.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderCustomRepository {
  final EntityManager entityManager;

  public Order anotherWay(Long id) {
    return (Order) entityManager.createQuery("""
            select o from Order o 
          left join fetch o.orderItems oi
          left join fetch oi.product
          left join fetch o.shippingAddress
          where o.customerId = :customerId
            """).setParameter("customerId", id).getSingleResult();
  }
}
