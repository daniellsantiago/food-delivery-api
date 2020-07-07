package com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jparepository;

import com.daniellsantiago.fooddeliveryapi.infrastructure.entity.CozinhaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CozinhaJpaRepository extends JpaRepository<CozinhaEntity, Long> {
}
