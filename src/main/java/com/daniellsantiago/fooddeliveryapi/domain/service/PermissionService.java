package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.exception.ResourceNotFoundException;
import com.daniellsantiago.fooddeliveryapi.domain.model.Permission;
import com.daniellsantiago.fooddeliveryapi.domain.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public Permission findById(Long id) {
        return permissionRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Permission with id " + id + " not found"));
    }
}
