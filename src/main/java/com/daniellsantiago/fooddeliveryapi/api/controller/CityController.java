package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.CityDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler.CityInputDisassembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.CityDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.CityInput;
import com.daniellsantiago.fooddeliveryapi.api.openapi.controller.CityControllerOpenApi;
import com.daniellsantiago.fooddeliveryapi.domain.model.City;
import com.daniellsantiago.fooddeliveryapi.domain.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/city", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CityController implements CityControllerOpenApi {
    private final CityService cityService;
    private final CityDTOAssembler cityDTOAssembler;
    private final CityInputDisassembler cityInputDisassembler;

    @PreAuthorize("hasAuthority('CUSTOMER_GENERIC')")
    @GetMapping
    public CollectionModel<CityDTO> findAll() {
        List<City> cities = cityService.findAll();

        return cityDTOAssembler.toCollectionModel(cities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> findById(@PathVariable Long id) {
        City city = cityService.findById(id);

        return ResponseEntity.ok(cityDTOAssembler.toModel(city));
    }

    @PostMapping
    public ResponseEntity<CityDTO> save(@RequestBody @Valid CityInput cityInput) {
        City city = cityInputDisassembler.toDomainObject(cityInput);

        city = cityService.save(city);

        CityDTO cityDTO = cityDTOAssembler.toModel(city);

        return new ResponseEntity<>(cityDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDTO> update(@RequestBody @Valid CityInput cityInput,
                                          @PathVariable Long id) {
        City cityToBeUpdated = cityService.findById(id);

        cityInputDisassembler.copyToDomainObject(cityInput, cityToBeUpdated);

        CityDTO updatedCity = cityDTOAssembler.toModel(cityService.save(cityToBeUpdated));

        return ResponseEntity.ok(updatedCity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}