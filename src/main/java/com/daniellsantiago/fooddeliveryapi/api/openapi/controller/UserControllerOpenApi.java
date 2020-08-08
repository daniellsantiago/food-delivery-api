package com.daniellsantiago.fooddeliveryapi.api.openapi.controller;

import com.daniellsantiago.fooddeliveryapi.api.dto.UserDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.PasswordInput;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.UserInput;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.UserWithPasswordInput;
import com.daniellsantiago.fooddeliveryapi.api.exceptionhandler.ExceptionDetails;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@Api(tags = "Users")
public interface UserControllerOpenApi {
    @ApiOperation("Find all Users")
    @ApiResponses({
        @ApiResponse(code = 204, message = "No User registered")
    })
    ResponseEntity<List<UserDTO>> findAll();

    @ApiOperation("Find one User by ID")
    @ApiResponses({
            @ApiResponse(code = 404, message = "User not found", response = ExceptionDetails.class),
            @ApiResponse(code = 400, message = "Invalid User ID", response = ExceptionDetails.class)
    })
    ResponseEntity<UserDTO> findById(@ApiParam(value = "User ID", example = "1", required = true)
                                     Long id);

    @ApiOperation("Save a new User to database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "User created"),
    })
    ResponseEntity<UserDTO> save(@ApiParam(name = "body", value = "Represents a new User", required = true)
                                 UserWithPasswordInput userWithPasswordInput);

    @ApiOperation("Update User information given an id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User updated"),
            @ApiResponse(code = 404, message = "User not found", response = ExceptionDetails.class)
    })
    ResponseEntity<UserDTO> update(@ApiParam(value = "User ID", example = "1", required = true)
                                   Long id,
                                   @ApiParam(name = "body", value = "Represents a new User", required = true)
                                   UserInput userInput);

    @ApiOperation("Change the User password")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Successfully changed the Password"),
            @ApiResponse(code = 404, message = "User not found", response = ExceptionDetails.class)
    })
    ResponseEntity<Void> changePassword(@ApiParam(value = "User ID", example = "1", required = true)
                                        Long id,
                                        @ApiParam(name = "body", value = "Password fields", required = true)
                                        PasswordInput password);
}
