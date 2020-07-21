package com.daniellsantiago.fooddeliveryapi.infrastructure.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.Role;
import com.daniellsantiago.fooddeliveryapi.domain.repository.RoleRepository;
import com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jpa.RoleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {
    private final RoleJpaRepository roleJpaRepository;

    @Override
    public Role save(Role entity) {
        return roleJpaRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        roleJpaRepository.deleteById(id);
        roleJpaRepository.flush();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleJpaRepository.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleJpaRepository.findAll();
    }
}
