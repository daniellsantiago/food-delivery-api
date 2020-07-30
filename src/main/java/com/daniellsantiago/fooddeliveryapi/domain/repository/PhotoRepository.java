package com.daniellsantiago.fooddeliveryapi.domain.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.PhotoProduct;

import java.util.Optional;

public interface PhotoRepository {
    PhotoProduct save(PhotoProduct photo);
    void delete(PhotoProduct photo);
    Optional<PhotoProduct> findById(Long restaurantId, Long productId);
}
