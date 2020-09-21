package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.RestaurantBasicDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.RestaurantDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler.RestaurantInputDisassembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.RestaurantBasicDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.RestaurantDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.RestaurantInput;
import com.daniellsantiago.fooddeliveryapi.api.openapi.controller.RestaurantControllerOpenApi;
import com.daniellsantiago.fooddeliveryapi.domain.model.Restaurant;
import com.daniellsantiago.fooddeliveryapi.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RestaurantController implements RestaurantControllerOpenApi {

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

    @GetMapping(params = "active=true")
    public ResponseEntity<List<RestaurantBasicDTO>> findAllActives() {
        List<RestaurantBasicDTO> restaurantBasicDTOS = restaurantBasicDTOAssembler.toCollectionDTO(restaurantService.findAllActives());
        if(restaurantBasicDTOS.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(restaurantBasicDTOS);
    }

    @GetMapping(params = "active=false")
    public ResponseEntity<List<RestaurantBasicDTO>> findAllInactive() {
        List<RestaurantBasicDTO> restaurantBasicDTOS = restaurantBasicDTOAssembler.toCollectionDTO(restaurantService.findAllInactive());
        if(restaurantBasicDTOS.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(restaurantBasicDTOS);
    }

    @GetMapping("/{id}")
    public RestaurantDTO findById(@PathVariable Long id) {
        return  restaurantDTOAssembler.toDTO(restaurantService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantDTO save(@RequestBody @Valid RestaurantInput restaurantInput) {
        Restaurant restaurant = restaurantInputDisassembler.toDomainObject(restaurantInput);
        restaurant = restaurantService.save(restaurant);
        return restaurantDTOAssembler.toDTO(restaurant);
    }

    @PutMapping("/{id}")
    public RestaurantDTO update(@PathVariable Long id,
                                                @RequestBody @Valid RestaurantInput restaurantInput){
        Restaurant restaurantToBeUpdated = restaurantService.findById(id);
        restaurantInputDisassembler.copyToDomainObject(restaurantInput, restaurantToBeUpdated);

        return restaurantDTOAssembler.toDTO(restaurantService.save(restaurantToBeUpdated));
    }

    @PutMapping("/{id}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long id) {
        restaurantService.activate(id);
    }

    @PutMapping("/{id}/opening")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void open(@PathVariable Long id) {
        restaurantService.open(id);
    }

    @PutMapping("/{id}/closure")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void close(@PathVariable Long id) {
        restaurantService.close(id);
    }

    @DeleteMapping("/{id}/inactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactivate(@PathVariable Long id) {
        restaurantService.inactivate(id);
    }
}
