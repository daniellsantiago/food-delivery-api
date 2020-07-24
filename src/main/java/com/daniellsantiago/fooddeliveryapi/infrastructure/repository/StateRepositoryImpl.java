package com.daniellsantiago.fooddeliveryapi.infrastructure.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.State;
import com.daniellsantiago.fooddeliveryapi.domain.repository.StateRepository;
import com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jpa.StateJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StateRepositoryImpl implements StateRepository {

    private final StateJpaRepository stateJpaRepository;

    @Override
    public State save(State entity) {
        return stateJpaRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        stateJpaRepository.deleteById(id);
        stateJpaRepository.flush();
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
