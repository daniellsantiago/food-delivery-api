package com.daniellsantiago.fooddeliveryapi.api.openapi.controller;

import com.daniellsantiago.fooddeliveryapi.api.exceptionhandler.ExceptionDetails;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

@Api(tags = "Orders")
public interface OrderFlowControllerOpenApi {

    @ApiOperation("Confirm Order")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Order confirmed successfully"),
            @ApiResponse(code = 404, message = "Order not found", response = ExceptionDetails.class)
    })
    void confirm(@ApiParam(value = "Order code", example = "f9981ca4-5a5e-4da3-af04-933861df3e55",
                                           required = true)
                                 String orderCode);

    @ApiOperation("Cancel Order")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Order cancelled successfully"),
            @ApiResponse(code = 404, message = "Order not found", response = ExceptionDetails.class)
    })
    void cancel(@ApiParam(value = "Order code", example = "f9981ca4-5a5e-4da3-af04-933861df3e55",
                                          required = true)
                                String orderCode);

    @ApiOperation("Deliver Order")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Order delivered successfully"),
            @ApiResponse(code = 404, message = "Order not found", response = ExceptionDetails.class)
    })
    void deliver(@ApiParam(value = "Order code", example = "f9981ca4-5a5e-4da3-af04-933861df3e55",
                                            required = true)
                                 String orderCode);
}
