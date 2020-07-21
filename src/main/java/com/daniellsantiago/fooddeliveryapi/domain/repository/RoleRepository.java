package com.daniellsantiago.fooddeliveryapi.domain.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    Role save(Role entity);
    void deleteById(Long id);
    Optional<Role> findById(Long id);
    List<Role> findAll();
}
