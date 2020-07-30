package com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jpa;

import com.daniellsantiago.fooddeliveryapi.domain.model.PhotoProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PhotoJpaRepository extends JpaRepository<PhotoProduct, Long> {

    @Query("select ph from PhotoProduct ph join ph.product p "
            + "where p.restaurant.id = :restaurantId and ph.product.id = :productId")
    Optional<PhotoProduct> findPhotoByIdRestaurantProduct(Long restaurantId, Long productId);
}
