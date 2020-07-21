package com.daniellsantiago.fooddeliveryapi.domain.repository;


import com.daniellsantiago.fooddeliveryapi.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User entity);
    Optional<User> findById(Long id);
    List<User> findAll();
}
