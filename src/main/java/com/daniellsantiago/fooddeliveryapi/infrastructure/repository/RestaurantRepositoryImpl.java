package com.daniellsantiago.fooddeliveryapi.infrastructure.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Restaurant;
import com.daniellsantiago.fooddeliveryapi.domain.repository.RestaurantRepository;
import com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jpa.RestaurantJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final RestaurantJpaRepository restaurantJpaRepository;

    @Override
    public Restaurant save(Restaurant entity) {
        return restaurantJpaRepository.save(entity);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantJpaRepository.findAll();
    }

    @Override
    public List<Restaurant> findAllActives() {
        return restaurantJpaRepository.findAllByActiveTrue();
    }

    @Override
    public List<Restaurant> findAllInactives() {
        return restaurantJpaRepository.findAllByActiveFalse();
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return restaurantJpaRepository.findById(id);
    }
}
