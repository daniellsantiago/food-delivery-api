package com.daniellsantiago.fooddeliveryapi.infrastructure.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.City;
import com.daniellsantiago.fooddeliveryapi.domain.repository.CityRepository;
import com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jpa.CityJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CityRepositoryImpl implements CityRepository {

    private final CityJpaRepository cityJpaRepository;

    @Override
    public City save(City entity) {
        return cityJpaRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        cityJpaRepository.deleteById(id);
        cityJpaRepository.flush();
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityJpaRepository.findById(id);
    }

    @Override
    public List<City> findAll() {
        return cityJpaRepository.findAll();
    }
}
