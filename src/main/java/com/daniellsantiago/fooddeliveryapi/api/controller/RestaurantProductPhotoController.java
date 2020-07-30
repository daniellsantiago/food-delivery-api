package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.PhotoProductDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.PhotoProductDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.PhotoProductInput;
import com.daniellsantiago.fooddeliveryapi.domain.model.PhotoProduct;
import com.daniellsantiago.fooddeliveryapi.domain.model.Product;
import com.daniellsantiago.fooddeliveryapi.domain.service.PhotoProductService;
import com.daniellsantiago.fooddeliveryapi.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/restaurant/{restaurantId}/product/{productId}/photo")
@RequiredArgsConstructor
public class RestaurantProductPhotoController {

    private final ProductService productService;

    private final PhotoProductService photoProductService;

    private final PhotoProductDTOAssembler photoProductDTOAssembler;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PhotoProductDTO updatePhoto(@PathVariable Long restaurantId,
                                       @PathVariable Long productId, @Valid PhotoProductInput photoProductInput) {
        Product product = productService.findById(restaurantId, productId);

        MultipartFile file = photoProductInput.getFile();

        PhotoProduct photoProduct = new PhotoProduct();
        photoProduct.setProduct(product);
        photoProduct.setDescription(photoProductInput.getDescription());
        photoProduct.setContentType(file.getContentType());
        photoProduct.setSize(file.getSize());
        photoProduct.setFilename(file.getOriginalFilename());

        PhotoProduct savedPhoto = photoProductService.save(photoProduct);

        return photoProductDTOAssembler.toDTO(savedPhoto);
    }
}
