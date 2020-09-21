package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.UserDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler.UserInputDisassembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.UserDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.PasswordInput;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.UserInput;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.UserWithPasswordInput;
import com.daniellsantiago.fooddeliveryapi.api.openapi.controller.UserControllerOpenApi;
import com.daniellsantiago.fooddeliveryapi.domain.model.User;
import com.daniellsantiago.fooddeliveryapi.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController implements UserControllerOpenApi {

    private final UserService userService;

    private final UserDTOAssembler userDTOAssembler;

    private final UserInputDisassembler userInputDisassembler;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userService.findAll();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(userDTOAssembler.toCollectionDTO(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        User user = userService.findById(id);

        return ResponseEntity.ok(userDTOAssembler.toDTO(user));
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody @Valid UserWithPasswordInput userWithPasswordInput) {
        User user = userInputDisassembler.toDomainObject(userWithPasswordInput);
        user = userService.save(user);

        return new ResponseEntity<>(userDTOAssembler.toDTO(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id,
                                  @RequestBody @Valid UserInput userInput) {
        User userToBeUpdated = userService.findById(id);
        userInputDisassembler.copyToDomainObject(userInput, userToBeUpdated);
        userToBeUpdated = userService.save(userToBeUpdated);

        return ResponseEntity.ok(userDTOAssembler.toDTO(userToBeUpdated));
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestBody @Valid PasswordInput password) {
        userService.changePassword(id, password.getOldPassword(), password.getNewPassword());
        return ResponseEntity.noContent().build();
    }
}
