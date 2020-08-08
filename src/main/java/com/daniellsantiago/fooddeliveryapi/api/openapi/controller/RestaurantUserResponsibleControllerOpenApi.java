package com.daniellsantiago.fooddeliveryapi.api.openapi.controller;

import com.daniellsantiago.fooddeliveryapi.api.dto.UserDTO;
import com.daniellsantiago.fooddeliveryapi.api.exceptionhandler.ExceptionDetails;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Restaurants")
public interface RestaurantUserResponsibleControllerOpenApi {

    @ApiOperation("Find all Users responsible to Restaurant")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid Restaurant ID", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "Restaurant not found", response = ExceptionDetails.class),
            @ApiResponse(code = 204, message = "No Users responsible to Restaurant found")
    })
    ResponseEntity<List<UserDTO>> findAll(@ApiParam(value = "Restaurant ID", example = "1", required = true)
                                          Long restaurantId);

    @ApiOperation("Associate user to be responsible to the restaurant")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid Restaurant or User ID", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "Restaurant or User not found", response = ExceptionDetails.class),
            @ApiResponse(code = 204, message = "Successfully associated",
                    response = ExceptionDetails.class)
    })
    ResponseEntity<Void> associate(@ApiParam(value = "Restaurant ID", example = "1", required = true)
                                   Long restaurantId,
                                   @ApiParam(value = "User ID", example = "1", required = true)
                                   Long userId);
    @ApiOperation("Disassociate user to be responsible to the restaurant")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid Restaurant or User ID", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "Restaurant or User not found", response = ExceptionDetails.class),
            @ApiResponse(code = 204, message = "Successfully disassociated",
                    response = ExceptionDetails.class)
    })
    ResponseEntity<Void> disassociate(@ApiParam(value = "Restaurant ID", example = "1", required = true)
                                              Long restaurantId,
                                      @ApiParam(value = "User ID", example = "1", required = true)
                                              Long userId);
}
