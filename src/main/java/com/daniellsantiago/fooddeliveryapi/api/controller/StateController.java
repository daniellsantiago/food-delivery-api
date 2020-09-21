package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.StateDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler.StateInputDisassembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.StateDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.StateInput;
import com.daniellsantiago.fooddeliveryapi.api.openapi.controller.StateControllerOpenApi;
import com.daniellsantiago.fooddeliveryapi.domain.model.State;
import com.daniellsantiago.fooddeliveryapi.domain.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/states", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class StateController implements StateControllerOpenApi {

    private final StateService stateService;

    private final StateDTOAssembler stateDTOAssembler;

    private final StateInputDisassembler stateInputDisassembler;

    @GetMapping
    public ResponseEntity<List<StateDTO>> findAll() {
        List<StateDTO> stateDTOS = stateDTOAssembler.toCollectionDTO(stateService.findAll());
        if(stateDTOS.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(stateDTOS);
    }

    @GetMapping("/{id}")
    public StateDTO findById(@PathVariable Long id) {
        return stateDTOAssembler.toDTO(stateService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StateDTO save(@RequestBody @Valid StateInput stateInput) {
        State state = stateInputDisassembler.toDomainObject(stateInput);

        return stateDTOAssembler.toDTO(stateService.save(state));
    }

    @PutMapping("/{id}")
    public StateDTO update(@PathVariable Long id, @RequestBody @Valid StateInput stateInput) {
        State stateToBeUpdated = stateService.findById(id);

        stateInputDisassembler.copyToDomainObject(stateInput, stateToBeUpdated);

        return stateDTOAssembler.toDTO(stateService.save(stateToBeUpdated));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        stateService.delete(id);
    }
}
