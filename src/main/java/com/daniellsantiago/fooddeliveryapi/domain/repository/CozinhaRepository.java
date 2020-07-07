package com.daniellsantiago.fooddeliveryapi.domain.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Cozinha;

import java.util.List;
import java.util.Optional;

public interface CozinhaRepository <T>{
    T save(T cozinha);
    T update(T cozinha);
    void deleteById(Long id);
    List<T> findAll();
    Optional<T> findById(long id);
}
