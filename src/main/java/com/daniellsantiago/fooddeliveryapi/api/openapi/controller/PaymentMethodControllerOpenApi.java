package com.daniellsantiago.fooddeliveryapi.api.openapi.controller;

import com.daniellsantiago.fooddeliveryapi.api.dto.PaymentMethodDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.PaymentMethodInput;
import com.daniellsantiago.fooddeliveryapi.api.exceptionhandler.ExceptionDetails;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.List;

@Api(tags = "Payment Methods")
public interface PaymentMethodControllerOpenApi {

    @ApiOperation("Find all Payment Methods")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Payment Method registered", response = ExceptionDetails.class)
    })
    ResponseEntity<List<PaymentMethodDTO>> findAll(ServletWebRequest request);

    @ApiOperation("Find one Payment Method by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid Payment Method ID", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "Payment Method not found", response = ExceptionDetails.class)
    })
    ResponseEntity<PaymentMethodDTO> findById(@ApiParam(value = "Payment Method ID", example = "1", required = true)
                                              Long id,
                                              ServletWebRequest request);
    @ApiOperation("Save a new Payment Method to database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Payment Method created"),
    })
    ResponseEntity<PaymentMethodDTO> save(@ApiParam(name = "body", value = "Represents a new Payment Method", required = true)
                                          PaymentMethodInput paymentMethodInput);

    @ApiOperation("Update Payment Method information given an id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Payment Method updated"),
            @ApiResponse(code = 404, message = "Payment Method not found", response = ExceptionDetails.class)
    })
    ResponseEntity<PaymentMethodDTO> update(@ApiParam(name = "body", value = "Represents a new Payment Method", required = true)
                                            PaymentMethodInput paymentMethodInput,
                                            @ApiParam(value = "Payment Method ID", example = "1", required = true)
                                            Long id);

    @ApiOperation("Delete a Payment Method given an id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid Payment Method ID", response = ExceptionDetails.class),
            @ApiResponse(code = 204, message = "Payment Method deleted"),
            @ApiResponse(code = 404, message = "Payment Method not found", response = ExceptionDetails.class)
    })
    ResponseEntity<Void> delete(@ApiParam(value = "Payment Method ID", example = "1", required = true)
                                Long id);
}
