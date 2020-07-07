package com.daniellsantiago.fooddeliveryapi.infrastructure.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Cozinha;
import com.daniellsantiago.fooddeliveryapi.domain.repository.CozinhaRepository;
import com.daniellsantiago.fooddeliveryapi.infrastructure.entity.CozinhaEntity;
import com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jparepository.CozinhaJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository<CozinhaEntity> {

    @Autowired
    private CozinhaJpaRepository cozinhaJpaRepository;


    @Override
    public CozinhaEntity save(CozinhaEntity cozinha) {
        return null;
    }

    @Override
    public CozinhaEntity update(CozinhaEntity cozinha) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<CozinhaEntity> findAll() {
        return null;
    }

    @Override
    public Optional<CozinhaEntity> findById(long id) {
        return Optional.empty();
    }
}
