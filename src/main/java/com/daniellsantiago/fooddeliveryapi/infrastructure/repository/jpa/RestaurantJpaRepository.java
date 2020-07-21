package com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jpa;

import com.daniellsantiago.fooddeliveryapi.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantJpaRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAllByActiveTrue();
    List<Restaurant> findAllByActiveFalse();
}
