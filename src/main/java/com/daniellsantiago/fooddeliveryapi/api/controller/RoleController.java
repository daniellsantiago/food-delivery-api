package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.RoleDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler.RoleInputDisassembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.RoleDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.RoleInput;
import com.daniellsantiago.fooddeliveryapi.api.openapi.controller.RoleControllerOpenApi;
import com.daniellsantiago.fooddeliveryapi.domain.model.Role;
import com.daniellsantiago.fooddeliveryapi.domain.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/roles", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RoleController implements RoleControllerOpenApi {

    private final RoleService roleService;

    private final RoleDTOAssembler roleDTOAssembler;

    private final RoleInputDisassembler roleInputDisassembler;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> findAll() {
        List<Role> roles = roleService.findAll();
        if(roles.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(roleDTOAssembler.toCollectionDTO(roles));
    }

    @GetMapping("/{id}")
    public RoleDTO findById(@PathVariable Long id) {
        return roleDTOAssembler.toDTO(roleService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDTO save(@RequestBody @Valid RoleInput roleInput) {
        Role role = roleInputDisassembler.toDomainObject(roleInput);

        return roleDTOAssembler.toDTO(roleService.save(role));
    }

    @PutMapping("/{id}")
    public RoleDTO update(@PathVariable Long id,
                                          @RequestBody @Valid RoleInput roleInput) {
        Role roleToBeUpdated = roleService.findById(id);

        roleInputDisassembler.copyToDomainObject(roleInput, roleToBeUpdated);

        return roleDTOAssembler.toDTO(roleService.save(roleToBeUpdated));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }
}
