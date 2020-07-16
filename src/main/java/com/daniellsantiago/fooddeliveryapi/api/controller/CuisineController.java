package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.CuisineDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler.CuisineInputDisassembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.CuisineDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.CuisineInput;
import com.daniellsantiago.fooddeliveryapi.domain.model.Cuisine;
import com.daniellsantiago.fooddeliveryapi.domain.service.CuisineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cuisine")
@RequiredArgsConstructor
public class CuisineController {
    private final CuisineService cuisineService;

    private final CuisineDTOAssembler cuisineDTOAssembler;

    private final CuisineInputDisassembler cuisineInputDisassembler;

    @GetMapping
    public ResponseEntity<List<CuisineDTO>> findAll() {
        List<Cuisine> cuisines = cuisineService.findAll();
        if(cuisines.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cuisineDTOAssembler.toCollectionDTO(cuisines));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuisineDTO> findById(@PathVariable Long id) {
        Cuisine cuisine = cuisineService.findById(id);
        return ResponseEntity.ok(cuisineDTOAssembler.toDTO(cuisine));
    }

    @PostMapping
    public ResponseEntity<CuisineDTO> save(@RequestBody @Valid CuisineInput cuisineInput) {
        Cuisine cuisineToBeSaved = cuisineInputDisassembler.toDomainObject(cuisineInput);
        cuisineService.save(cuisineToBeSaved);
        return new ResponseEntity<>(cuisineDTOAssembler.toDTO(cuisineToBeSaved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuisineDTO> update(@RequestBody @Valid CuisineInput cuisineInput, @PathVariable Long id) {
        Cuisine cuisineToBeUpdated = cuisineService.findById(id);
        cuisineInputDisassembler.copyToDomainObject(cuisineInput, cuisineToBeUpdated);

        Cuisine updatedCuisine = cuisineService.save(cuisineToBeUpdated);

        return ResponseEntity.ok(cuisineDTOAssembler.toDTO(updatedCuisine));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cuisineService.delete(id);

        return ResponseEntity.noContent().build();
    }
 }
