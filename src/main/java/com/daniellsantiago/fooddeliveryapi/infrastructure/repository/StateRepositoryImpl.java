package com.daniellsantiago.fooddeliveryapi.infrastructure.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.State;
import com.daniellsantiago.fooddeliveryapi.domain.repository.StateRepository;
import com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jpa.StateJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StateRepositoryImpl implements StateRepository {
    @Autowired
    private StateJpaRepository stateJpaRepository;

    @Override
    public State save(State entity) {
        return stateJpaRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        stateJpaRepository.deleteById(id);
    }

    @Override
    public Optional<State> findById(Long id) {
        return stateJpaRepository.findById(id);
    }

    @Override
    public List<State> findAll() {
        return stateJpaRepository.findAll();
    }
}
