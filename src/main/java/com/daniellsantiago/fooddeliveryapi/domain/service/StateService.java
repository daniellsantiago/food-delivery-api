package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.exception.EntityInUse;
import com.daniellsantiago.fooddeliveryapi.domain.exception.ResourceNotFoundException;
import com.daniellsantiago.fooddeliveryapi.domain.model.State;
import com.daniellsantiago.fooddeliveryapi.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;

    @Transactional
    public State save(State state) {
        return stateRepository.save(state);
    }

    @Transactional
    public void delete(Long stateId) {
        try {
            stateRepository.deleteById(stateId);
        }catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("State with id " + stateId + " not found");
        }catch (DataIntegrityViolationException e) {
            throw new EntityInUse("State with id " + stateId + " is associated with another Entity");
        }
    }

    public State findById(Long id) {
        return stateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("State with id " + id + " not found"));
    }
}
