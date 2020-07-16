package com.daniellsantiago.fooddeliveryapi.infrastructure.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Restaurant;
import com.daniellsantiago.fooddeliveryapi.domain.repository.RestaurantRepository;
import com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jpa.RestaurantJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @Autowired
    private RestaurantJpaRepository restaurantJpaRepository;

    @Override
    public Restaurant save(Restaurant entity) {
        return restaurantJpaRepository.save(entity);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantJpaRepository.findAll();
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return restaurantJpaRepository.findById(id);
    }
}
