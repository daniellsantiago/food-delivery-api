package com.daniellsantiago.fooddeliveryapi.api.openapi.controller;

import com.daniellsantiago.fooddeliveryapi.api.dto.RoleDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.RoleInput;
import com.daniellsantiago.fooddeliveryapi.api.exceptionhandler.ExceptionDetails;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@Api(tags = "Roles")
public interface RoleControllerOpenApi {

    @ApiOperation("Find all Roles")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Roles registered", response = ExceptionDetails.class)
    })
    ResponseEntity<List<RoleDTO>> findAll();

    @ApiOperation("Find one Role by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid Role ID", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "Role not found", response = ExceptionDetails.class)
    })
    ResponseEntity<RoleDTO> findById(@ApiParam(value = "Role ID", example = "1")
                                     Long id);

    @ApiOperation("Save a new Role to database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Role created"),
    })
    ResponseEntity<RoleDTO> save(@ApiParam(name = "body", value = "Represents a new Role")
                                 RoleInput roleInput);

    @ApiOperation("Update Role informations given an id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Role updated"),
            @ApiResponse(code = 404, message = "Role not found", response = ExceptionDetails.class)
    })
    ResponseEntity<RoleDTO> update(@ApiParam(value = "Role ID", example = "1")
                                   Long id,
                                   @ApiParam(name = "body", value = "Represents a new Role")
                                   RoleInput roleInput);

    @ApiOperation("Delete a Role given an id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Role deleted"),
            @ApiResponse(code = 404, message = "Role not found", response = ExceptionDetails.class)
    })
    ResponseEntity<Void> delete(@ApiParam(value = "Role ID", example = "1")
                                Long id);
}