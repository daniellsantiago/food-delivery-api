package com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jpa;

import com.daniellsantiago.fooddeliveryapi.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
