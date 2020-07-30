package com.daniellsantiago.fooddeliveryapi.infrastructure.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.PhotoProduct;
import com.daniellsantiago.fooddeliveryapi.domain.repository.PhotoRepository;
import com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jpa.PhotoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PhotoRepositoryImpl implements PhotoRepository {
    private final PhotoJpaRepository photoJpaRepository;

    @Override
    public PhotoProduct save(PhotoProduct photo) {
        PhotoProduct photoSaved = photoJpaRepository.save(photo);
        photoJpaRepository.flush();
        return photoSaved;
    }

    @Override
    public void delete(PhotoProduct photo) {
        photoJpaRepository.delete(photo);
    }

    @Override
    public Optional<PhotoProduct> findById(Long restaurantId, Long productId) {
        return photoJpaRepository.findPhotoByIdRestaurantProduct(restaurantId, productId);
    }
}
