package com.daniellsantiago.fooddeliveryapi.domain.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Cuisine;

import java.util.List;
import java.util.Optional;

public interface CuisineRepository {
    Cuisine save(Cuisine entity);
    void deleteById(Long id);
    List<Cuisine> findAll();
    Optional<Cuisine> findById(Long id);
}
