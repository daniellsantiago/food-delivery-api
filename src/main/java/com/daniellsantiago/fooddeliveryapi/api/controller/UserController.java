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
    public UserDTO findById(@PathVariable Long id) {
        return userDTOAssembler.toDTO(userService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO save(@RequestBody @Valid UserWithPasswordInput userWithPasswordInput) {
        User user = userInputDisassembler.toDomainObject(userWithPasswordInput);

        return userDTOAssembler.toDTO(userService.save(user));
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Long id,
                                  @RequestBody @Valid UserInput userInput) {
        User userToBeUpdated = userService.findById(id);
        userInputDisassembler.copyToDomainObject(userInput, userToBeUpdated);

        return userDTOAssembler.toDTO(userService.save(userToBeUpdated));
    }

    @PutMapping("/{id}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@PathVariable Long id, @RequestBody @Valid PasswordInput password) {
        userService.changePassword(id, password.getOldPassword(), password.getNewPassword());
    }
}
