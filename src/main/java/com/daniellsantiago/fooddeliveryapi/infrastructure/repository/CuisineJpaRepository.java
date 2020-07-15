package com.daniellsantiago.fooddeliveryapi.infrastructure.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuisineJpaRepository extends JpaRepository<Cuisine, Long> {
}
