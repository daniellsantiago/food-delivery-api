package com.daniellsantiago.fooddeliveryapi.api.assembler;

import com.daniellsantiago.fooddeliveryapi.api.dto.PermissionDTO;
import com.daniellsantiago.fooddeliveryapi.domain.model.Permission;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PermissionDTOAssembler {
    private final ModelMapper modelMapper;

    public PermissionDTO toModel(Permission permission) {
        return modelMapper.map(permission, PermissionDTO.class);
    }

    public List<PermissionDTO> toCollectionDTO(Collection<Permission> permissions) {
        return permissions.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
