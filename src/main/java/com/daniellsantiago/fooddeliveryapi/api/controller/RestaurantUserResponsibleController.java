package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.UserDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.UserDTO;
import com.daniellsantiago.fooddeliveryapi.domain.model.Restaurant;
import com.daniellsantiago.fooddeliveryapi.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant/{restaurantId}/responsible")
@RequiredArgsConstructor
public class RestaurantUserResponsibleController {
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

    @PutMapping("/{userId}")
    public ResponseEntity<Void> associate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.associateResponsible(restaurantId, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> disassociate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.disassociateResponsible(restaurantId, userId);
        return ResponseEntity.noContent().build();
    }
}
