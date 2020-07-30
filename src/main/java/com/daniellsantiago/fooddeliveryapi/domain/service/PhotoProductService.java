package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.model.PhotoProduct;
import com.daniellsantiago.fooddeliveryapi.domain.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoProductService {

    private final PhotoRepository photoRepository;

    @Transactional
    public PhotoProduct save(PhotoProduct photo) {
        Long restaurantId = photo.getRestaurantId();
        Long productId = photo.getProduct().getId();

        Optional<PhotoProduct> photoFound = photoRepository
                .findById(restaurantId, productId);

        photoFound.ifPresent(photoRepository::delete);

        return photoRepository.save(photo);
    }
}
