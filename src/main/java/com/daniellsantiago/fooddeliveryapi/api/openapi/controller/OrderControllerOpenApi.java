package com.daniellsantiago.fooddeliveryapi.api.openapi.controller;

import com.daniellsantiago.fooddeliveryapi.api.dto.OrderBasicDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.OrderDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.OrderInput;
import com.daniellsantiago.fooddeliveryapi.api.exceptionhandler.ExceptionDetails;
import com.daniellsantiago.fooddeliveryapi.domain.filter.OrderFilter;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@Api(tags = "Orders")
public interface OrderControllerOpenApi {

    @ApiOperation("Find all Orders")
    Page<OrderBasicDTO> findAll(OrderFilter filter,
                                Pageable pageable);

    @ApiOperation("Find one Order by code")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Order not found", response = ExceptionDetails.class)
    })
    ResponseEntity<OrderDTO> findByCode(@ApiParam(value = "Order Code", example = "f9981ca4-5a5e-4da3-af04-933861df3e55"
                                                    , required = true)
                                        String code);

    @ApiOperation("Save a new Order to database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Order created"),
    })
    ResponseEntity<OrderDTO> add(@ApiParam(name = "body", value = "Represents a new Order", required = true)
                                 OrderInput orderInput);
}
