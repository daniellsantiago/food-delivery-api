package com.daniellsantiago.fooddeliveryapi.infrastructure.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.City;
import com.daniellsantiago.fooddeliveryapi.domain.model.Cuisine;
import com.daniellsantiago.fooddeliveryapi.domain.repository.CityRepository;
import com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jpa.CityJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CityRepositoryImpl implements CityRepository {
    @Autowired
    private CityJpaRepository cityJpaRepository;


    @Override
    public City save(City entity) {
        return cityJpaRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        cityJpaRepository.deleteById(id);
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityJpaRepository.findById(id);
    }
}
