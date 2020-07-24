package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.exception.InvalidDataRequestException;
import com.daniellsantiago.fooddeliveryapi.domain.exception.ResourceNotFoundException;
import com.daniellsantiago.fooddeliveryapi.domain.model.Role;
import com.daniellsantiago.fooddeliveryapi.domain.model.User;
import com.daniellsantiago.fooddeliveryapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Transactional
    public User save(User user) {
        try {
            return userRepository.save(user);
        } catch(DataIntegrityViolationException ex) {
            throw new InvalidDataRequestException("Email already in use");
        }
    }

    @Transactional
    public void changePassword(Long id, String oldPassword, String newPassword) {
        User user = findById(id);

        if(user.passwordNotEquals(oldPassword)) {
            throw new InvalidDataRequestException("Incorrect Password");
        }

        user.setPassword(newPassword);
    }

    @Transactional
    public void associateRole(Long userId, Long roleId) {
        User user = findById(userId);
        Role role = roleService.findById(roleId);

        user.addRole(role);
    }

    @Transactional
    public void disassociateRole(Long userId, Long roleId) {
        User user = findById(userId);
        Role role = roleService.findById(roleId);

        user.removeRole(role);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }
}
