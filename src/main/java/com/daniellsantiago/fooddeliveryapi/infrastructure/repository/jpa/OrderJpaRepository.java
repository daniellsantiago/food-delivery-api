package com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jpa;

import com.daniellsantiago.fooddeliveryapi.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByCode(String code);

    @Query("from Order o join fetch o.customer join fetch o.restaurant r join fetch r.cuisine")
    List<Order> findAll();
}
