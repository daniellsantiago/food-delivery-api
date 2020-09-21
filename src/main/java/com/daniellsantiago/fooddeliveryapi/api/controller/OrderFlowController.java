package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.openapi.controller.OrderFlowControllerOpenApi;
import com.daniellsantiago.fooddeliveryapi.domain.service.OrderFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/orders/{orderCode}", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderFlowController implements OrderFlowControllerOpenApi {
    private final OrderFlowService orderFlowService;

    @PutMapping("/confirm")
    public ResponseEntity<Void> confirm(@PathVariable String orderCode) {
        orderFlowService.confirm(orderCode);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cancel")
    public ResponseEntity<Void> cancel(@PathVariable String orderCode) {
        orderFlowService.cancel(orderCode);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/deliver")
    public ResponseEntity<Void> deliver(@PathVariable String orderCode) {
        orderFlowService.deliver(orderCode);
        return ResponseEntity.noContent().build();
    }
}
