package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.exception.ResourceNotFoundException;
import com.daniellsantiago.fooddeliveryapi.domain.model.PhotoProduct;
import com.daniellsantiago.fooddeliveryapi.domain.repository.PhotoRepository;
import com.daniellsantiago.fooddeliveryapi.domain.service.PhotoStorageService.NewPhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoProductService {

    private final PhotoRepository photoRepository;

    private final PhotoStorageService photoStorageService;

    @Transactional
    public PhotoProduct save(PhotoProduct photo, InputStream fileData) {
        Long restaurantId = photo.getRestaurantId();
        Long productId = photo.getProduct().getId();

        String newFilename = photoStorageService.generateFilename(photo.getFilename());
        String fileFoundName = null;

        Optional<PhotoProduct> photoFound = photoRepository
                .findById(restaurantId, productId);

        if (photoFound.isPresent()) {
            fileFoundName = photoFound.get().getFilename();
            photoRepository.delete(photoFound.get());
        }

        photo.setFilename(newFilename);
        photo = photoRepository.save(photo);

        NewPhoto newPhoto = NewPhoto.builder()
                            .filename(photo.getFilename())
                            .inputStream(fileData)
                            .build();
        photoStorageService.substitute(fileFoundName, newPhoto);

        return photo;
    }

    public PhotoProduct findById(Long restaurantId, Long productId) {
        return photoRepository.findById(restaurantId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Photo not found"));
    }

    @Transactional
    public void delete(Long restaurantId, Long productId) {
        PhotoProduct photo = findById(restaurantId, productId);

        photoRepository.delete(photo);

        photoStorageService.remove(photo.getFilename());
    }

}
