package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.PhotoProductDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.PhotoProductDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.PhotoProductInput;
import com.daniellsantiago.fooddeliveryapi.api.openapi.controller.RestaurantProductPhotoControllerOpenApi;
import com.daniellsantiago.fooddeliveryapi.domain.exception.ResourceNotFoundException;
import com.daniellsantiago.fooddeliveryapi.domain.model.PhotoProduct;
import com.daniellsantiago.fooddeliveryapi.domain.model.Product;
import com.daniellsantiago.fooddeliveryapi.domain.service.PhotoProductService;
import com.daniellsantiago.fooddeliveryapi.domain.service.PhotoStorageService;
import com.daniellsantiago.fooddeliveryapi.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurant/{restaurantId}/product/{productId}/photo", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RestaurantProductPhotoController implements RestaurantProductPhotoControllerOpenApi {

    private final ProductService productService;

    private final PhotoProductService photoProductService;

    private final PhotoProductDTOAssembler photoProductDTOAssembler;

    private final PhotoStorageService photoStorageService;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PhotoProductDTO updatePhoto(@PathVariable Long restaurantId,
                                       @PathVariable Long productId, @Valid PhotoProductInput photoProductInput,
                                       @RequestPart(required = true) MultipartFile file) throws IOException {
        Product product = productService.findById(restaurantId, productId);

        //MultipartFile file = photoProductInput.getFile();

        PhotoProduct photo = new PhotoProduct();
        photo.setProduct(product);
        photo.setDescription(photoProductInput.getDescription());
        photo.setContentType(file.getContentType());
        photo.setSize(file.getSize());
        photo.setFilename(file.getOriginalFilename());

        PhotoProduct savedPhoto = photoProductService.save(photo, file.getInputStream());

        return photoProductDTOAssembler.toDTO(savedPhoto);
    }

    @GetMapping
    public PhotoProductDTO findOne(@PathVariable Long restaurantId,
                                   @PathVariable Long productId) {
        PhotoProduct photo = photoProductService.findById(restaurantId, productId);

        return photoProductDTOAssembler.toDTO(photo);
    }

    @GetMapping(produces = MediaType.ALL_VALUE)
    public ResponseEntity<InputStreamResource> sendPhoto(@PathVariable Long restaurantId,
                                                          @PathVariable Long productId,
                                                         @RequestHeader(name = "accept") String acceptHeader) {
        try {
            PhotoProduct photo = photoProductService.findById(restaurantId, productId);

            MediaType mediaType = MediaType.parseMediaType(photo.getContentType());
            List<MediaType> mediaTypesAccept = MediaType.parseMediaTypes(acceptHeader);

            verifyMediaType(mediaType, mediaTypesAccept);

            InputStream inputStream = photoStorageService.recovery(photo.getFilename());

            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .body(new InputStreamResource(inputStream));
        } catch (ResourceNotFoundException | HttpMediaTypeNotAcceptableException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long restaurantId,
                        @PathVariable Long productId) {
        photoProductService.delete(restaurantId, productId);
    }

    private void verifyMediaType(MediaType mediaType,
                                 List<MediaType> mediaTypesAccept) throws HttpMediaTypeNotAcceptableException {

        boolean compatible = mediaTypesAccept.stream()
                .anyMatch(mediaTypeAccept -> mediaTypeAccept.isCompatibleWith(mediaType));

        if (!compatible) {
            throw new HttpMediaTypeNotAcceptableException(mediaTypesAccept);
        }
    }
}
