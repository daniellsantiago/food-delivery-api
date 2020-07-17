package com.daniellsantiago.fooddeliveryapi.domain.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.City;

import java.util.List;
import java.util.Optional;

public interface CityRepository {
    City save(City entity);
    void deleteById(Long id);
    Optional<City> findById(Long id);
    List<City> findAll();
}
