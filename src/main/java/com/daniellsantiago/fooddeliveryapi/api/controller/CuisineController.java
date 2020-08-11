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
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/cuisine", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CuisineController implements CuisineControllerOpenApi {
    private final CuisineService cuisineService;

    private final CuisineDTOAssembler cuisineDTOAssembler;

    private final CuisineInputDisassembler cuisineInputDisassembler;

    private final PagedResourcesAssembler<Cuisine> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<CuisineDTO> findAll(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cuisine> cuisinePage = cuisineService.findAll(pageable);

        return pagedResourcesAssembler.toModel(cuisinePage, cuisineDTOAssembler);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuisineDTO> findById(@PathVariable Long id) {
        Cuisine cuisine = cuisineService.findById(id);
        return ResponseEntity.ok(cuisineDTOAssembler.toModel(cuisine));
    }

    @PostMapping
    public ResponseEntity<CuisineDTO> save(@RequestBody @Valid CuisineInput cuisineInput) {
        Cuisine cuisineToBeSaved = cuisineInputDisassembler.toDomainObject(cuisineInput);
        cuisineToBeSaved = cuisineService.save(cuisineToBeSaved);
        return new ResponseEntity<>(cuisineDTOAssembler.toModel(cuisineToBeSaved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuisineDTO> update(@RequestBody @Valid CuisineInput cuisineInput, @PathVariable Long id) {
        Cuisine cuisineToBeUpdated = cuisineService.findById(id);
        cuisineInputDisassembler.copyToDomainObject(cuisineInput, cuisineToBeUpdated);

        Cuisine updatedCuisine = cuisineService.save(cuisineToBeUpdated);

        return ResponseEntity.ok(cuisineDTOAssembler.toModel(updatedCuisine));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cuisineService.delete(id);

        return ResponseEntity.noContent().build();
    }
 }
