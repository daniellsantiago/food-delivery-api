package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.exception.ResourceNotFoundException;
import com.daniellsantiago.fooddeliveryapi.domain.model.Cozinha;
import com.daniellsantiago.fooddeliveryapi.domain.repository.GenericCrudRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CozinhaService {
    private final GenericCrudRepository<Cozinha, Long> genericCrudRepository;

    public Cozinha save(Cozinha cozinha) {
        return genericCrudRepository.save(cozinha);
    }

    public Cozinha update(Cozinha cozinha) {
        return genericCrudRepository.update(cozinha);
    }

    public List<Cozinha> findAll() {
        return genericCrudRepository.findAll();
    }

    public Cozinha findById(Long id) {
        return genericCrudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cozinha com o id " + id + " não encontrada"));
    }

    public void delete(Long id) {
        Cozinha cozinha = genericCrudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cozinha com o id " + id + " não encontrada"));
        genericCrudRepository.deleteById(cozinha.getId());
    }
}
