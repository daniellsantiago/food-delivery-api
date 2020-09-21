package com.daniellsantiago.fooddeliveryapi.api.openapi.controller;

import com.daniellsantiago.fooddeliveryapi.api.dto.ProductDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.ProductInput;
import com.daniellsantiago.fooddeliveryapi.api.exceptionhandler.ExceptionDetails;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@Api(tags = "Products")
public interface RestaurantProductControllerOpenApi {

    @ApiOperation("Find All Products from a Restaurant")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Product associated with Restaurant found"),
            @ApiResponse(code = 400, message = "Invalid Restaurant ID", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "Restaurant not found", response = ExceptionDetails.class)
    })
    ResponseEntity<List<ProductDTO>> findAll(@ApiParam(value = "Restaurant ID", example = "1", required = true)
                                             Long restaurantId,
                                             @ApiParam(value = "Include inactive products in the response",
                                                      allowableValues = "true, false", defaultValue = "false")
                                             boolean includeInactive);

    @ApiOperation("Find one Product from a Restaurant by IDs")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid Restaurant or Product ID", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "Restaurant or Product not found", response = ExceptionDetails.class)
    })
    ProductDTO findProductById(@ApiParam(value = "Restaurant ID", example = "1", required = true)
                                               Long restaurantId,
                                               @ApiParam(value = "Product ID", example = "1", required = true)
                                               Long productId);

    @ApiOperation("Save a new Product to database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Product created"),
            @ApiResponse(code = 404, message = "Restaurant not found", response = ExceptionDetails.class)
    })
    ProductDTO save(@ApiParam(value = "Restaurant ID", example = "1", required = true)
                                    Long restaurantId,
                                    @ApiParam(name = "body", value = "Represents a new Product", required = true)
                                    ProductInput productInput);

    @ApiOperation("Update a Product from a Restaurant")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product updated"),
            @ApiResponse(code = 404, message = "Product from Restaurant not found", response = ExceptionDetails.class)
    })
    ProductDTO update(@ApiParam(value = "Restaurant ID", example = "1", required = true)
                                      Long restaurantId,
                                      @ApiParam(value = "Product ID", example = "1", required = true)
                                      Long productId,
                                      @ApiParam(name = "body", value = "Represents a new Product", required = true)
                                      ProductInput productInput);
}
