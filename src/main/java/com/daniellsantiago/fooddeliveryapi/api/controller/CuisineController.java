package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.CuisineDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler.CuisineInputDisassembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.CuisineDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.CuisineInput;
import com.daniellsantiago.fooddeliveryapi.api.openapi.controller.CuisineControllerOpenApi;
import com.daniellsantiago.fooddeliveryapi.domain.model.Cuisine;
import com.daniellsantiago.fooddeliveryapi.domain.service.CuisineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/cuisines", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CuisineController implements CuisineControllerOpenApi {
    private final CuisineService cuisineService;

    private final CuisineDTOAssembler cuisineDTOAssembler;

    private final CuisineInputDisassembler cuisineInputDisassembler;

    @GetMapping
    public Page<CuisineDTO> findAll(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cuisine> cuisinePage = cuisineService.findAll(pageable);
        return cuisineDTOAssembler.toCollectionDTO(cuisinePage);
    }

    @GetMapping("/{id}")
    public CuisineDTO findById(@PathVariable Long id) {
        return cuisineDTOAssembler.toDTO(cuisineService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CuisineDTO save(@RequestBody @Valid CuisineInput cuisineInput) {
        Cuisine cuisineToBeSaved = cuisineInputDisassembler.toDomainObject(cuisineInput);
        return cuisineDTOAssembler.toDTO(cuisineService.save(cuisineToBeSaved));
    }

    @PutMapping("/{id}")
    public CuisineDTO update(@RequestBody @Valid CuisineInput cuisineInput, @PathVariable Long id) {
        Cuisine cuisineToBeUpdated = cuisineService.findById(id);
        cuisineInputDisassembler.copyToDomainObject(cuisineInput, cuisineToBeUpdated);

        Cuisine updatedCuisine = cuisineService.save(cuisineToBeUpdated);

        return cuisineDTOAssembler.toDTO(updatedCuisine);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cuisineService.delete(id);
    }
 }
