package com.daniellsantiago.fooddeliveryapi.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions = new HashSet<>();

    public boolean addPermission(Permission permission) {
        return getPermissions().add(permission);
    }

    public boolean removePermission(Permission permission) {
        return getPermissions().remove(permission);
    }
}
