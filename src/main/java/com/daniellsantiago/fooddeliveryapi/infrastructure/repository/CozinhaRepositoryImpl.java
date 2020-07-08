package com.daniellsantiago.fooddeliveryapi.infrastructure.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Cozinha;
import com.daniellsantiago.fooddeliveryapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @Autowired
    private CozinhaJpaRepository cozinhaJpaRepository;

    @Override
    public Cozinha save(Cozinha entity) {
        return cozinhaJpaRepository.save(entity);
    }

    @Override
    public Cozinha update(Cozinha entity) {
        return cozinhaJpaRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        cozinhaJpaRepository.deleteById(id);
    }

    @Override
    public List<Cozinha> findAll() {
        return cozinhaJpaRepository.findAll();
    }

    @Override
    public Optional<Cozinha> findById(Long id) {
        return cozinhaJpaRepository.findById(id);
    }
}
