package com.daniellsantiago.fooddeliveryapi.infrastructure.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Cuisine;
import com.daniellsantiago.fooddeliveryapi.domain.repository.CuisineRepository;
import com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jpa.CuisineJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CuisineRepositoryImpl implements CuisineRepository {

    @Autowired
    private CuisineJpaRepository cuisineJpaRepository;

    @Override
    public Cuisine save(Cuisine entity) {
        return cuisineJpaRepository.save(entity);
    }

    @Override
    public Cuisine update(Cuisine entity) {
        return cuisineJpaRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        cuisineJpaRepository.deleteById(id);
    }

    @Override
    public List<Cuisine> findAll() {
        return cuisineJpaRepository.findAll();
    }

    @Override
    public Optional<Cuisine> findById(Long id) {
        return cuisineJpaRepository.findById(id);
    }
}
