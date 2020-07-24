package com.daniellsantiago.fooddeliveryapi.infrastructure.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Product;
import com.daniellsantiago.fooddeliveryapi.domain.model.Restaurant;
import com.daniellsantiago.fooddeliveryapi.domain.repository.ProductRepository;
import com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jpa.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Optional<Product> findById(Long restaurantId, Long productId) {
        return productJpaRepository.findById(restaurantId, productId);
    }

    @Override
    public List<Product> findAllByRestaurant(Restaurant restaurant) {
        return productJpaRepository.findAllByRestaurant(restaurant);
    }

    @Override
    public List<Product> findActivesByRestaurant(Restaurant restaurant) {
        return productJpaRepository.findActivesByRestaurant(restaurant);
    }

    @Override
    public Product save(Product entity) {
        return productJpaRepository.save(entity);
    }
}
