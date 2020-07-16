package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.exception.EntityInUse;
import com.daniellsantiago.fooddeliveryapi.domain.exception.ResourceNotFoundException;
import com.daniellsantiago.fooddeliveryapi.domain.model.Cuisine;
import com.daniellsantiago.fooddeliveryapi.domain.repository.CuisineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CuisineService {
    private final CuisineRepository cuisineRepository;

    @Transactional
    public Cuisine save(Cuisine cuisine) {
        return cuisineRepository.save(cuisine);
    }

    public List<Cuisine> findAll() {
        return cuisineRepository.findAll();
    }

    public Cuisine findById(Long id) {
        return cuisineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuisine with id " + id + " not found"));
    }

    @Transactional
    public void delete(Long id) {
        try {
            cuisineRepository.deleteById(id);
        }catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Cuisine with id " + id + " not found");
        }catch(DataIntegrityViolationException e){
            throw new EntityInUse("Cuisine with id " + id + " is associated with another Entity");
        }
    }
}
