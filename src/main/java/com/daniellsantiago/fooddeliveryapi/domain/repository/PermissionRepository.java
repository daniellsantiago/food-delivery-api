package com.daniellsantiago.fooddeliveryapi.domain.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Permission;

import java.util.Optional;

public interface PermissionRepository {
    Optional<Permission> findById(Long id);
}
