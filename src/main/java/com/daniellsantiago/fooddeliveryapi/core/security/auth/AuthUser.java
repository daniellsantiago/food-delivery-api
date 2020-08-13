package com.daniellsantiago.fooddeliveryapi.core.security.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AuthUser extends User {

    private static final long serialVersionUID = 1L;

    private final Long userId;
    private final String fullName;

    public AuthUser(com.daniellsantiago.fooddeliveryapi.domain.model.User user,
                    Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);

        this.userId = user.getId();
        this.fullName = user.getName();
    }

}
