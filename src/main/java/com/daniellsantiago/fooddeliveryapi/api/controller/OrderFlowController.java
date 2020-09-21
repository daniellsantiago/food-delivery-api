package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.openapi.controller.OrderFlowControllerOpenApi;
import com.daniellsantiago.fooddeliveryapi.domain.service.OrderFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/orders/{orderCode}", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderFlowController implements OrderFlowControllerOpenApi {
    private final OrderFlowService orderFlowService;

    @PutMapping("/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@PathVariable String orderCode) {
        orderFlowService.confirm(orderCode);
    }

    @PutMapping("/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable String orderCode) {
        orderFlowService.cancel(orderCode);
    }

    @PutMapping("/deliver")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliver(@PathVariable String orderCode) {
        orderFlowService.deliver(orderCode);
    }
}
