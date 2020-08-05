package com.daniellsantiago.fooddeliveryapi.api.openapi.controller;

import com.daniellsantiago.fooddeliveryapi.api.dto.CuisineDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.CuisineInput;
import com.daniellsantiago.fooddeliveryapi.api.exceptionhandler.ExceptionDetails;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@Api(tags = "Cuisines")
public interface CuisineControllerOpenApi {

    @ApiOperation("Find all Cuisines with pagination")
    Page<CuisineDTO> findAll(Pageable pageable);

    @ApiOperation("Find one Cuisine by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid Cuisine ID", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "Cuisine not found", response = ExceptionDetails.class)
    })
    ResponseEntity<CuisineDTO> findById(@ApiParam(value = "Cuisine ID", example = "1", required = true)
                                        Long id);

    @ApiOperation("Save a new Cuisine to database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cuisine created"),
    })
    ResponseEntity<CuisineDTO> save(@ApiParam(name = "body", value = "Represents a new Cuisine", required = true)
                                    CuisineInput cuisineInput);

    @ApiOperation("Update Cuisine information given an id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cuisine updated"),
            @ApiResponse(code = 404, message = "Cuisine not found", response = ExceptionDetails.class)
    })
    ResponseEntity<CuisineDTO> update(@ApiParam(name = "body", value = "Represents a new Cuisine", required = true)
                                      CuisineInput cuisineInput,
                                      @ApiParam(value = "Cuisine ID", example = "1", required = true)
                                      Long id);
    @ApiOperation("Delete a Cuisine given an id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cuisine deleted"),
            @ApiResponse(code = 404, message = "Cuisine not found", response = ExceptionDetails.class)
    })
    ResponseEntity<Void> delete(@ApiParam(value = "Cuisine ID", required = true)
                                Long id);
}
