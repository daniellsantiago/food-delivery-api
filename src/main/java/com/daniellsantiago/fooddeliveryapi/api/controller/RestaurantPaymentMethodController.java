package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.PaymentMethodDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.PaymentMethodDTO;
import com.daniellsantiago.fooddeliveryapi.api.openapi.controller.RestaurantPaymentMethodControllerOpenApi;
import com.daniellsantiago.fooddeliveryapi.domain.model.Restaurant;
import com.daniellsantiago.fooddeliveryapi.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/restaurants/{restaurantId}/payment-method", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RestaurantPaymentMethodController implements RestaurantPaymentMethodControllerOpenApi {

    private final RestaurantService restaurantService;

    private final PaymentMethodDTOAssembler paymentMethodDTOAssembler;

    @GetMapping
    public ResponseEntity<List<PaymentMethodDTO>> findAll(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        List<PaymentMethodDTO> paymentMethodDTOS =
                paymentMethodDTOAssembler.toCollectionDTO(restaurant.getPaymentMethods());

        if(paymentMethodDTOS.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(paymentMethodDTOS);
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> disassociate(@PathVariable Long restaurantId, @PathVariable Long paymentId) {
        restaurantService.disassociatePaymentMethod(restaurantId, paymentId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<Void> associate(@PathVariable Long restaurantId, @PathVariable Long paymentId) {
        restaurantService.associatePaymentMethod(restaurantId, paymentId);
        return ResponseEntity.noContent().build();
    }
}
