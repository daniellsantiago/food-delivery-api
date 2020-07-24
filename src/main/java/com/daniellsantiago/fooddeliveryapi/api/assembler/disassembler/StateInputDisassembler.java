package com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler;

import com.daniellsantiago.fooddeliveryapi.api.dto.input.StateInput;
import com.daniellsantiago.fooddeliveryapi.domain.model.State;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StateInputDisassembler {

    private final ModelMapper modelMapper;

    public State toDomainObject(StateInput stateInput) {
        return modelMapper.map(stateInput, State.class);
    }

    public void copyToDomainObject(StateInput stateInput, State state) {
        modelMapper.map(stateInput, state);
    }
}
