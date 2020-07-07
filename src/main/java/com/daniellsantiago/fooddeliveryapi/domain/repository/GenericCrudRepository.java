package com.daniellsantiago.fooddeliveryapi.domain.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Cozinha;

import java.util.List;
import java.util.Optional;

public interface GenericCrudRepository<T, ID>{
    T save(T entity);
    T update(T entity);
    void deleteById(ID id);
    List<T> findAll();
    Optional<T> findById(ID id);
}
