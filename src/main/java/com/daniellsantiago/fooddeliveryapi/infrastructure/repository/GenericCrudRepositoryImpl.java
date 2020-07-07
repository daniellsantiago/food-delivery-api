package com.daniellsantiago.fooddeliveryapi.infrastructure.repository;

import com.daniellsantiago.fooddeliveryapi.domain.repository.GenericCrudRepository;
import com.daniellsantiago.fooddeliveryapi.infrastructure.entity.CozinhaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenericCrudRepositoryImpl<T, ID> implements GenericCrudRepository<T, ID> {

    private final DataJpaRepository<T, ID> dataJpaRepository;

    @Override
    public T save(T entity) {
        return dataJpaRepository.save(entity);
    }

    @Override
    public T update(T entity) {
        return dataJpaRepository.save(entity);
    }

    @Override
    public void deleteById(ID id) {
        dataJpaRepository.deleteById(id);
    }

    @Override
    public List<T> findAll() {
        return dataJpaRepository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return dataJpaRepository.findById(id);
    }
}
