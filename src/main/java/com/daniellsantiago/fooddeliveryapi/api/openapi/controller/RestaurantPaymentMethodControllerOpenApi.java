package com.daniellsantiago.fooddeliveryapi.api.openapi.controller;

import com.daniellsantiago.fooddeliveryapi.api.dto.PaymentMethodDTO;
import com.daniellsantiago.fooddeliveryapi.api.exceptionhandler.ExceptionDetails;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Restaurants")
public interface RestaurantPaymentMethodControllerOpenApi {

    @ApiOperation("Find all Payment Methods associated with a Restaurant")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid Restaurant ID", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "Restaurant not found", response = ExceptionDetails.class),
            @ApiResponse(code = 204, message = "No Payment Method associated with Restaurant found")
    })
    ResponseEntity<List<PaymentMethodDTO>> findAll(@ApiParam(value = "Restaurant ID", example = "1", required = true)
                                                   Long restaurantId);

    @ApiOperation("Associate Payment Method to Restaurant")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid Restaurant or Payment Method ID", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "Restaurant or Payment Method not found", response = ExceptionDetails.class),
            @ApiResponse(code = 204, message = "Successfully associated",
                    response = ExceptionDetails.class)
    })
    void associate(@ApiParam(value = "Restaurant ID", example = "1", required = true)
                                   Long restaurantId,
                                   @ApiParam(value = "Payment Method ID", example = "1", required = true)
                                   Long paymentId);

    @ApiOperation("Disassociate Payment Method to Restaurant")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid Restaurant or Payment Method ID", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "Restaurant or Payment Method not found", response = ExceptionDetails.class),
            @ApiResponse(code = 204, message = "Successfully disassociated",
                    response = ExceptionDetails.class)
    })
    void disassociate(@ApiParam(value = "Restaurant ID", example = "1", required = true)
                                      Long restaurantId,
                                      @ApiParam(value = "Payment Method ID", example = "1", required = true)
                                      Long paymentId);
}
