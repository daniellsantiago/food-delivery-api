package com.daniellsantiago.fooddeliveryapi.domain.repository;


import com.daniellsantiago.fooddeliveryapi.domain.model.State;

import java.util.Optional;

public interface StateRepository {
    State save(State entity);
    void deleteById(Long id);
    Optional<State> findById(Long id);
}
