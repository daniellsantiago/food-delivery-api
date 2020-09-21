package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.UserDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.UserDTO;
import com.daniellsantiago.fooddeliveryapi.api.openapi.controller.RestaurantUserResponsibleControllerOpenApi;
import com.daniellsantiago.fooddeliveryapi.domain.model.Restaurant;
import com.daniellsantiago.fooddeliveryapi.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/restaurants/{restaurantId}/responsible", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RestaurantUserResponsibleController implements RestaurantUserResponsibleControllerOpenApi {
    private final RestaurantService restaurantService;

    private final UserDTOAssembler userDTOAssembler;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findById(restaurantId);

        List<UserDTO> userDTOS = userDTOAssembler.toCollectionDTO(restaurant.getResponsibles());

        if(userDTOS.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(userDTOS);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.disassociateResponsible(restaurantId, userId);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.associateResponsible(restaurantId, userId);
    }
}
