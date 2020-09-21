package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.PermissionDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.PermissionDTO;
import com.daniellsantiago.fooddeliveryapi.api.openapi.controller.RolePermissionControllerOpenApi;
import com.daniellsantiago.fooddeliveryapi.domain.model.Role;
import com.daniellsantiago.fooddeliveryapi.domain.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/roles/{roleId}/permission", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RolePermissionController implements RolePermissionControllerOpenApi {

    private final RoleService roleService;

    private final PermissionDTOAssembler permissionDTOAssembler;

    @GetMapping
    public ResponseEntity<List<PermissionDTO>> findAll(@PathVariable Long roleId) {
        Role role = roleService.findById(roleId);

        List<PermissionDTO> permissionDTOS = permissionDTOAssembler.toCollectionDTO(role.getPermissions());

        if(permissionDTOS.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(permissionDTOS);
    }

    @PutMapping("/{permissionId}")
    public ResponseEntity<Void> associate(@PathVariable Long roleId, @PathVariable Long permissionId) {
        roleService.associatePermission(roleId, permissionId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{permissionId}")
    public ResponseEntity<Void> disassociate(@PathVariable Long roleId, @PathVariable Long permissionId) {
        roleService.disassociatePermission(roleId, permissionId);
        return ResponseEntity.noContent().build();
    }
}
