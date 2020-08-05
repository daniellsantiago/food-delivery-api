package com.daniellsantiago.fooddeliveryapi.api.openapi.controller;

import com.daniellsantiago.fooddeliveryapi.api.dto.RestaurantBasicDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.RestaurantDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.RestaurantInput;
import com.daniellsantiago.fooddeliveryapi.api.exceptionhandler.ExceptionDetails;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api(tags = "Restaurants")
public interface RestaurantControllerOpenApi {
    @ApiOperation("Find all Restaurants")
    @ApiImplicitParam(value = "Filter Restaurants by active property", name = "active", paramType = "query",
                      type = "string", allowableValues = "true, false")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Restaurant registered", response = ExceptionDetails.class)
    })
    ResponseEntity<List<RestaurantBasicDTO>> findAll();

    @ApiOperation(value = "Find Actives Restaurants", hidden = true)
    ResponseEntity<List<RestaurantBasicDTO>> findAllActives();

    @ApiOperation(value = "Find Inactives Restaurants", hidden = true)
    ResponseEntity<List<RestaurantBasicDTO>> findAllInactives();

    @ApiOperation("Find one Restaurant by ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid Restaurant ID", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "Restaurant not found", response = ExceptionDetails.class)
    })
    ResponseEntity<RestaurantDTO> findById(@ApiParam(value = "Restaurant ID", example = "1", required = true)
                                           Long id);
    @ApiOperation("Save a new Restaurant to database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Restaurant created"),
    })
    ResponseEntity<RestaurantDTO> save(@ApiParam(name = "body", value = "Represents a new Restaurant", required = true)
                                       RestaurantInput restaurantInput);

    @ApiOperation("Update Restaurant informations given an id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Restaurant updated"),
            @ApiResponse(code = 404, message = "Restaurant not found", response = ExceptionDetails.class)
    })
    ResponseEntity<RestaurantDTO> update(@ApiParam(value = "Restaurant ID", example = "1", required = true)
                                         Long id,
                                         @ApiParam(name = "body", value = "Represents a new Restaurant", required = true)
                                         RestaurantInput restaurantInput);

    @ApiOperation("Activates a restaurant by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Activated Restaurant"),
            @ApiResponse(code = 404, message = "Restaurant not found", response = ExceptionDetails.class)
    })
    ResponseEntity<Void> activate(@ApiParam(value = "Restaurant ID", example = "1", required = true)
                                  Long id);

    @ApiOperation("Inactivates a restaurant by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Inactivated Restaurant"),
            @ApiResponse(code = 404, message = "Restaurant not found", response = ExceptionDetails.class)
    })
    ResponseEntity<Void> inactivate(@ApiParam(value = "Restaurant ID", example = "1", required = true)
                                    Long id);

    @ApiOperation("Open a restaurant by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurant has been opened"),
            @ApiResponse(code = 404, message = "Restaurant not found", response = ExceptionDetails.class)
    })
    ResponseEntity<Void> open(@ApiParam(value = "Restaurant ID", example = "1", required = true)
                              Long id);

    @ApiOperation("Close a restaurant by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurant has been closed"),
            @ApiResponse(code = 404, message = "Restaurant not found", response = ExceptionDetails.class)
    })
    ResponseEntity<Void> close(@ApiParam(value = "Restaurant ID", example = "1", required = true)
                               Long id);
}