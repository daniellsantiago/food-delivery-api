package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.exception.ResourceNotFoundException;
import com.daniellsantiago.fooddeliveryapi.domain.model.Product;
import com.daniellsantiago.fooddeliveryapi.domain.model.Restaurant;
import com.daniellsantiago.fooddeliveryapi.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAllByRestaurant(Restaurant restaurant) {
        return productRepository.findAllByRestaurant(restaurant);
    }

    public List<Product> findActivesByRestaurant(Restaurant restaurant) {
        return productRepository.findActivesByRestaurant(restaurant);
    }

    public Product findById(Long restaurantId, Long productId) {
        return productRepository.findById(restaurantId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + productId +
                        " not found at Restaurant with id " + restaurantId));
    }

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
