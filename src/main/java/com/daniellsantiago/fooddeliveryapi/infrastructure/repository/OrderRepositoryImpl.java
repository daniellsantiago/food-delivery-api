package com.daniellsantiago.fooddeliveryapi.infrastructure.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Order;
import com.daniellsantiago.fooddeliveryapi.domain.repository.OrderRepository;
import com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jpa.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(Order order) {
        return orderJpaRepository.save(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderJpaRepository.findById(id);
    }

    @Override
    public Optional<Order> findByCode(String code) {
        return orderJpaRepository.findByCode(code);
    }

    @Override
    public List<Order> findAll() {
        return orderJpaRepository.findAll();
    }
}
