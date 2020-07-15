package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.domain.model.Cuisine;
import com.daniellsantiago.fooddeliveryapi.domain.service.CuisineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinha")
@RequiredArgsConstructor
public class CuisineController {
    private final CuisineService cuisineService;

    @PostMapping
    public ResponseEntity<Cuisine> save(@RequestBody Cuisine cuisine) {
        return new ResponseEntity<>(cuisineService.save(cuisine), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cuisine>> findAll() {
        return ResponseEntity.ok(cuisineService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuisine> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cuisineService.findById(id));
    }
 }
