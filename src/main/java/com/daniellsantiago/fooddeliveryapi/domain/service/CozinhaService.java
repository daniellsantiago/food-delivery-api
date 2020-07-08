package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.exception.ResourceNotFoundException;
import com.daniellsantiago.fooddeliveryapi.domain.model.Cozinha;
import com.daniellsantiago.fooddeliveryapi.domain.repository.CozinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CozinhaService {
    private final CozinhaRepository cozinhaRepository;

    public Cozinha save(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public Cozinha update(Cozinha cozinha) {
        return cozinhaRepository.update(cozinha);
    }

    public List<Cozinha> findAll() {
        return cozinhaRepository.findAll();
    }

    public Cozinha findById(Long id) {
        return cozinhaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cozinha com o id " + id + " não encontrada"));
    }

    public void delete(Long id) {
        Cozinha cozinha = cozinhaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cozinha com o id " + id + " não encontrada"));
        cozinhaRepository.deleteById(cozinha.getId());
    }
}
