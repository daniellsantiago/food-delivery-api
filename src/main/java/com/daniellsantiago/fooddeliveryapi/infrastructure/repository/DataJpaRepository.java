package com.daniellsantiago.fooddeliveryapi.infrastructure.repository;

import com.daniellsantiago.fooddeliveryapi.infrastructure.entity.CozinhaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataJpaRepository<T, ID> extends JpaRepository<T, ID> {
}
