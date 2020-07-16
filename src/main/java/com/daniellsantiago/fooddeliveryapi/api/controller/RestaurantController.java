package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.RestaurantBasicDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.RestaurantDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler.RestaurantInputDisassembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.RestaurantBasicDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.RestaurantDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.RestaurantInput;
import com.daniellsantiago.fooddeliveryapi.domain.model.Restaurant;
import com.daniellsantiago.fooddeliveryapi.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    private final RestaurantDTOAssembler restaurantDTOAssembler;

    private final RestaurantBasicDTOAssembler restaurantBasicDTOAssembler;

    private final RestaurantInputDisassembler restaurantInputDisassembler;

    @GetMapping
    public ResponseEntity<List<RestaurantBasicDTO>> findAll() {
        List<RestaurantBasicDTO> restaurantBasicDTOS = restaurantBasicDTOAssembler.toCollectionDTO(restaurantService.findAll());
        if(restaurantBasicDTOS.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(restaurantBasicDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> findById(@PathVariable Long id) {
        RestaurantDTO restaurantDTO = restaurantDTOAssembler.toDTO(restaurantService.findById(id));
        return  ResponseEntity.ok(restaurantDTO);
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> save(@RequestBody @Valid RestaurantInput restaurantInput) {
        Restaurant restaurant = restaurantInputDisassembler.toDomainObject(restaurantInput);
        Restaurant savedRestaurant = restaurantService.save(restaurant);
        return new ResponseEntity<>(restaurantDTOAssembler.toDTO(savedRestaurant), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> update(@PathVariable Long id,
                                                @RequestBody @Valid RestaurantInput restaurantInput){
        Restaurant restaurantToBeUpdated = restaurantService.findById(id);
        restaurantInputDisassembler.copyToDomainObject(restaurantInput, restaurantToBeUpdated);

        RestaurantDTO updatedRestaurant = restaurantDTOAssembler.toDTO(restaurantService.save(restaurantToBeUpdated));
        return ResponseEntity.ok(updatedRestaurant);
    }
}
