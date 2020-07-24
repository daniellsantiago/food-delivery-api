package com.daniellsantiago.fooddeliveryapi.domain.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Product;
import com.daniellsantiago.fooddeliveryapi.domain.model.Restaurant;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(Long restaurantId, Long productId);

    List<Product> findAllByRestaurant(Restaurant restaurant);

    List<Product> findActivesByRestaurant(Restaurant restaurant);

    Product save(Product entity);
}
