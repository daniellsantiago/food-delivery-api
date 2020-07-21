package com.daniellsantiago.fooddeliveryapi.api.assembler;

import com.daniellsantiago.fooddeliveryapi.api.dto.RoleDTO;
import com.daniellsantiago.fooddeliveryapi.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleDTOAssembler {

    private final ModelMapper modelMapper;

    public RoleDTO toDTO(Role role) {
        return modelMapper.map(role, RoleDTO.class);
    }

    public List<RoleDTO> toCollectionDTO(List<Role> roles) {
        return roles.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
