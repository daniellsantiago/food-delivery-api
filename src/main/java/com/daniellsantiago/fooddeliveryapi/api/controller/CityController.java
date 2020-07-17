package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.CityDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler.CityInputDisassembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.CityDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.CityInput;
import com.daniellsantiago.fooddeliveryapi.domain.model.City;
import com.daniellsantiago.fooddeliveryapi.domain.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    private final CityDTOAssembler cityDTOAssembler;
    private final CityInputDisassembler cityInputDisassembler;

    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll() {
        List<City> cities = cityService.findAll();
        if(cities.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cityDTOAssembler.toCollectionDTO(cities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cityDTOAssembler.toDTO(cityService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CityDTO> save(@RequestBody @Valid CityInput cityInput) {
        City city = cityInputDisassembler.toDomainObject(cityInput);

        city = cityService.save(city);

        return new ResponseEntity<>(cityDTOAssembler.toDTO(city), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDTO> update(@RequestBody @Valid CityInput cityInput, @PathVariable Long id) {
        City cityToBeUpdated = cityService.findById(id);

        cityInputDisassembler.copyToDomainObject(cityInput, cityToBeUpdated);

        CityDTO updatedCity = cityDTOAssembler.toDTO(cityService.save(cityToBeUpdated));

        return ResponseEntity.ok(updatedCity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}