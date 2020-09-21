package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.CityDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler.CityInputDisassembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.CityDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.CityInput;
import com.daniellsantiago.fooddeliveryapi.api.openapi.controller.CityControllerOpenApi;
import com.daniellsantiago.fooddeliveryapi.domain.model.City;
import com.daniellsantiago.fooddeliveryapi.domain.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/cities", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CityController implements CityControllerOpenApi {
    private final CityService cityService;
    private final CityDTOAssembler cityDTOAssembler;
    private final CityInputDisassembler cityInputDisassembler;

    @GetMapping
    public List<CityDTO> findAll() {
        List<City> cities = cityService.findAll();

        return cityDTOAssembler.toCollectionDTO(cities);
    }

    @GetMapping("/{id}")
    public CityDTO findById(@PathVariable Long id) {
        City city = cityService.findById(id);

        return cityDTOAssembler.toDTO(city);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityDTO save(@RequestBody @Valid CityInput cityInput) {
        City city = cityInputDisassembler.toDomainObject(cityInput);

        city = cityService.save(city);

        CityDTO cityDTO = cityDTOAssembler.toDTO(city);

        return cityDTO;
    }

    @PutMapping("/{id}")
    public CityDTO update(@RequestBody @Valid CityInput cityInput,
                                          @PathVariable Long id) {
        City cityToBeUpdated = cityService.findById(id);

        cityInputDisassembler.copyToDomainObject(cityInput, cityToBeUpdated);

        return cityDTOAssembler.toDTO(cityService.save(cityToBeUpdated));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cityService.delete(id);
    }
}