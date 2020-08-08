package com.daniellsantiago.fooddeliveryapi.api.openapi.controller;

import com.daniellsantiago.fooddeliveryapi.api.dto.PhotoProductDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.PhotoProductInput;
import com.daniellsantiago.fooddeliveryapi.api.exceptionhandler.ExceptionDetails;
import io.swagger.annotations.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = "Products")
public interface RestaurantProductPhotoControllerOpenApi {

    @ApiOperation(value = "Find the Product Photo from a Restaurant",
            produces = "application/json, image/jpeg, image/png")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid Restaurant or Product ID", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "The Product Photo could not be found", response = ExceptionDetails.class)
    })
    PhotoProductDTO findOne(@ApiParam(value = "Restaurant ID", example = "1", required = true)
                            Long restaurantId,
                            @ApiParam(value = "Product ID", example = "1", required = true)
                            Long productId);

    @ApiOperation(value = "Find the Product Photo from a Restaurant", hidden = true)
    ResponseEntity<InputStreamResource> sendPhoto(Long restaurantId,
                                                  Long productId,
                                                  String acceptHeader) throws HttpMediaTypeNotAcceptableException;

    @ApiOperation("Update the Product Photo from a Restaurant")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product Photo updated"),
            @ApiResponse(code = 404, message = "Product not found", response = ExceptionDetails.class)
    })
    PhotoProductDTO updatePhoto(@ApiParam(value = "Restaurant ID", example = "1", required = true)
                                Long restaurantId,
                                @ApiParam(value = "Product ID", example = "1", required = true)
                                Long productId,
                                PhotoProductInput photoProductInput,
                                @ApiParam(value = "Product photo file (maximum 500KB, JPG and PNG only)",
                                         required = true)
                                MultipartFile file) throws IOException;

    @ApiOperation("Delete the Product Photo from a Restaurant")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Product Photo deleted"),
            @ApiResponse(code = 400, message = "Invalid Restaurant or Product ID", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "The Product Photo could not be found", response = ExceptionDetails.class)
    })
    void delete(@ApiParam(value = "Restaurant ID", example = "1", required = true)
                Long restaurantId,
                @ApiParam(value = "Product ID", example = "1", required = true)
                Long productId);
}