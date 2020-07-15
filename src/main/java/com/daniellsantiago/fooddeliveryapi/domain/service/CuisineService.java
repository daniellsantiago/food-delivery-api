package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.exception.ResourceNotFoundException;
import com.daniellsantiago.fooddeliveryapi.domain.model.Cuisine;
import com.daniellsantiago.fooddeliveryapi.domain.repository.CuisineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CuisineService {
    private final CuisineRepository cuisineRepository;

    public Cuisine save(Cuisine cuisine) {
        return cuisineRepository.save(cuisine);
    }

    public Cuisine update(Cuisine cuisine) {
        return cuisineRepository.update(cuisine);
    }

    public List<Cuisine> findAll() {
        return cuisineRepository.findAll();
    }

    public Cuisine findById(Long id) {
        return cuisineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuisine with id " + id + " not found"));
    }

    public void delete(Long id) {
        Cuisine cuisine = cuisineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuisine with id " + id + " not found"));
        cuisineRepository.deleteById(cuisine.getId());
    }
}
