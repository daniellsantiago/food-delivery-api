package com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler;

import com.daniellsantiago.fooddeliveryapi.api.dto.input.RoleInput;
import com.daniellsantiago.fooddeliveryapi.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleInputDisassembler {
    private final ModelMapper modelMapper;

    public Role toDomainObject(RoleInput roleInput) {
        return modelMapper.map(roleInput, Role.class);
    }

    public void copyToDomainObject(RoleInput roleInput, Role role) {
        modelMapper.map(roleInput, role);
    }
}
