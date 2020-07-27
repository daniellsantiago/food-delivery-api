package com.daniellsantiago.fooddeliveryapi.domain.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(Long id);
    Optional<Order> findByCode(String code);
    List<Order> findAll();
}
