package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.exception.EntityInUseException;
import com.daniellsantiago.fooddeliveryapi.domain.exception.ResourceNotFoundException;
import com.daniellsantiago.fooddeliveryapi.domain.model.Permission;
import com.daniellsantiago.fooddeliveryapi.domain.model.Role;
import com.daniellsantiago.fooddeliveryapi.domain.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final PermissionService permissionService;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role with id " + id + " not found"));
    }

    @Transactional
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public void delete(Long id) {
        try {
            roleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Role with id " + id + " not found");

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException("Role with id " + id +
                    " cannot be deleted because it is associated with another Entity");
        }
    }

    @Transactional
    public void associatePermission(Long roleId, Long permissionId) {
        Role role = findById(roleId);
        Permission permission = permissionService.findById(permissionId);

        role.addPermission(permission);
    }

    @Transactional
    public void disassociatePermission(Long roleId, Long permissionId) {
        Role role = findById(roleId);
        Permission permission = permissionService.findById(permissionId);

        role.removePermission(permission);
    }
}
