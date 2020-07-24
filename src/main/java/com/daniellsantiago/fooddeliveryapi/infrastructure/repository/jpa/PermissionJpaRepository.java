package com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jpa;

import com.daniellsantiago.fooddeliveryapi.domain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionJpaRepository extends JpaRepository<Permission, Long> {
}
