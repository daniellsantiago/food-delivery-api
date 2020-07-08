package com.daniellsantiago.fooddeliveryapi.domain.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Cozinha;

import java.util.List;
import java.util.Optional;

public interface CozinhaRepository {
    Cozinha save(Cozinha entity);
    Cozinha update(Cozinha entity);
    void deleteById(Long id);
    List<Cozinha> findAll();
    Optional<Cozinha> findById(Long id);
}
