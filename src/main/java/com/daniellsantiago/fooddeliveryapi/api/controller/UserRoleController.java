package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.RoleDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.RoleDTO;
import com.daniellsantiago.fooddeliveryapi.api.openapi.controller.UserRoleControllerOpenApi;
import com.daniellsantiago.fooddeliveryapi.domain.model.User;
import com.daniellsantiago.fooddeliveryapi.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users/{userId}/role", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRoleController implements UserRoleControllerOpenApi {
    private final UserService userService;

    private final RoleDTOAssembler roleDTOAssembler;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> findAll(@PathVariable Long userId) {
        User user = userService.findById(userId);
        List<RoleDTO> roleDTOS = roleDTOAssembler.toCollectionDTO(user.getRoles());
        if(roleDTOS.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(roleDTOS);
    }

    @PutMapping("/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long userId, @PathVariable Long roleId) {
        userService.associateRole(userId, roleId);
    }

    @DeleteMapping("/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long userId, @PathVariable Long roleId) {
        userService.disassociateRole(userId, roleId);
    }
}
