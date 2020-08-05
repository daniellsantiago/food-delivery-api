package com.daniellsantiago.fooddeliveryapi.infrastructure.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Cuisine;
import com.daniellsantiago.fooddeliveryapi.domain.repository.CuisineRepository;
import com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jpa.CuisineJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CuisineRepositoryImpl implements CuisineRepository {

    private final CuisineJpaRepository cuisineJpaRepository;

    @Override
    public Cuisine save(Cuisine entity) {
        return cuisineJpaRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        cuisineJpaRepository.deleteById(id);
        cuisineJpaRepository.flush();
    }

    @Override
    public List<Cuisine> findAll() {
        return cuisineJpaRepository.findAll();
    }

    @Override
    public Page<Cuisine> findAll(Pageable pageable) {
        return cuisineJpaRepository.findAll(pageable);
    }

    @Override
    public Optional<Cuisine> findById(Long id) {
        return cuisineJpaRepository.findById(id);
    }
}
