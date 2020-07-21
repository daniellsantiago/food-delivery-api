package com.daniellsantiago.fooddeliveryapi.domain.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {
    Restaurant save(Restaurant entity);
    List<Restaurant> findAll();
    List<Restaurant> findAllActives();
    List<Restaurant> findAllInactives();
    Optional<Restaurant> findById(Long id);
}
