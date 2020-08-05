package com.daniellsantiago.fooddeliveryapi.domain.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Cuisine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CuisineRepository {
    Cuisine save(Cuisine entity);
    void deleteById(Long id);
    List<Cuisine> findAll();
    Page<Cuisine> findAll(Pageable pageable);
    Optional<Cuisine> findById(Long id);
}
