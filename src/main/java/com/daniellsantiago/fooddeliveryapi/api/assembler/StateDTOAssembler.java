package com.daniellsantiago.fooddeliveryapi.api.assembler;

import com.daniellsantiago.fooddeliveryapi.api.dto.StateDTO;
import com.daniellsantiago.fooddeliveryapi.domain.model.State;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StateDTOAssembler {

    private final ModelMapper modelMapper;

    public StateDTO toDTO(State state) {
        return modelMapper.map(state, StateDTO.class);
    }

    public List<StateDTO> toCollectionDTO(List<State> states) {
        return states.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
