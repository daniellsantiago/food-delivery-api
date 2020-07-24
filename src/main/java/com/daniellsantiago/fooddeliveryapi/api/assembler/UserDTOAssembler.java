package com.daniellsantiago.fooddeliveryapi.api.assembler;

import com.daniellsantiago.fooddeliveryapi.api.dto.UserDTO;
import com.daniellsantiago.fooddeliveryapi.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDTOAssembler {
    private final ModelMapper modelMapper;

    public UserDTO toDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> toCollectionDTO(Collection<User> users) {
        return users.stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
    }
}
