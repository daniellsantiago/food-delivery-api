package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.domain.model.Cozinha;
import com.daniellsantiago.fooddeliveryapi.domain.service.CozinhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinha")
@RequiredArgsConstructor
public class CozinhaController {
    private final CozinhaService cozinhaService;

    @PostMapping
    public ResponseEntity<Cozinha> save(@RequestBody Cozinha cozinha) {
        return new ResponseEntity<>(cozinhaService.save(cozinha), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cozinha>> findAll() {
        return ResponseEntity.ok(cozinhaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cozinhaService.findById(id));
    }
 }
