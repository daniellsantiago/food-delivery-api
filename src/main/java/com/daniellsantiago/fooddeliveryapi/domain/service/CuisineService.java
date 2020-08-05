package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.exception.EntityInUseException;
import com.daniellsantiago.fooddeliveryapi.domain.exception.ResourceNotFoundException;
import com.daniellsantiago.fooddeliveryapi.domain.model.Cuisine;
import com.daniellsantiago.fooddeliveryapi.domain.repository.CuisineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Cuisine> findAll(Pageable pageable) {
        return cuisineRepository.findAll(pageable);
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
            throw new EntityInUseException("Cuisine with id " + id +
                    " cannot be deleted because it is associated with another Entity");
        }
    }
}
