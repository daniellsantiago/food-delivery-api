package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.exception.ResourceNotFoundException;
import com.daniellsantiago.fooddeliveryapi.domain.model.Cuisine;
import com.daniellsantiago.fooddeliveryapi.domain.model.Restaurant;
import com.daniellsantiago.fooddeliveryapi.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final CuisineService cuisineService;

    public Restaurant save(Restaurant restaurant) {
        Long cuisine_id = restaurant.getCuisine().getId();

        Cuisine cuisine = cuisineService.findById(cuisine_id);

        restaurant.setCuisine(cuisine);

        return restaurantRepository.save(restaurant);
    }

    public Restaurant update(Restaurant restaurant) {
        Cuisine cuisine = cuisineService.findById(restaurant.getCuisine().getId());
        restaurant.setCuisine(cuisine);
        return restaurantRepository.update(restaurant);
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant with id " + id + " not found"));
    }
}
