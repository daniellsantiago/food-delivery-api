package com.daniellsantiago.fooddeliveryapi.infrastructure.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Permission;
import com.daniellsantiago.fooddeliveryapi.domain.repository.PermissionRepository;
import com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jpa.PermissionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PermissionRepositoryImpl implements PermissionRepository {
    private final PermissionJpaRepository permissionJpaRepository;

    @Override
    public Optional<Permission> findById(Long id) {
        return permissionJpaRepository.findById(id);
    }
}
