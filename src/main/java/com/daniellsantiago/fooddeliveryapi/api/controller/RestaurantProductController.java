package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.ProductDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler.ProductInputDisassembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.ProductDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.ProductInput;
import com.daniellsantiago.fooddeliveryapi.api.openapi.controller.RestaurantProductControllerOpenApi;
import com.daniellsantiago.fooddeliveryapi.domain.model.Product;
import com.daniellsantiago.fooddeliveryapi.domain.model.Restaurant;
import com.daniellsantiago.fooddeliveryapi.domain.service.ProductService;
import com.daniellsantiago.fooddeliveryapi.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/restaurants/{restaurantId}/product", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RestaurantProductController implements RestaurantProductControllerOpenApi {
    private final ProductService productService;

    private final RestaurantService restaurantService;

    private final ProductDTOAssembler productDTOAssembler;

    private final ProductInputDisassembler productInputDisassembler;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll(@PathVariable Long restaurantId,
                                                    @RequestParam(required = false) boolean includeInactive){
        Restaurant restaurant = restaurantService.findById(restaurantId);

        List<Product> allProducts = null;

        if(includeInactive) {
            allProducts = productService.findAllByRestaurant(restaurant);
        } else {
            allProducts = productService.findActivesByRestaurant(restaurant);
        }

        if(allProducts.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(productDTOAssembler.toCollectionDTO(allProducts));
    }

    @GetMapping("/{productId}")
    public ProductDTO findProductById(@PathVariable Long restaurantId,
                                                      @PathVariable Long productId) {
        return productDTOAssembler.toDTO(productService.findById(restaurantId, productId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO save(@PathVariable Long restaurantId,
                                           @RequestBody @Valid ProductInput productInput) {

        Restaurant restaurant = restaurantService.findById(restaurantId);

        Product product = productInputDisassembler.toDomainObject(productInput);
        product.setRestaurant(restaurant);

        return productDTOAssembler.toDTO(productService.save(product));
    }

    @PutMapping("/{productId}")
    public ProductDTO update(@PathVariable Long restaurantId, @PathVariable Long productId,
                                  @RequestBody @Valid ProductInput productInput) {
        Product product = productService.findById(restaurantId, productId);

        productInputDisassembler.copyToDomainObject(productInput, product);

        return productDTOAssembler.toDTO(productService.save(product));
    }
}
