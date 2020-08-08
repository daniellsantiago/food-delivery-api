package com.daniellsantiago.fooddeliveryapi.api.openapi.controller;

import com.daniellsantiago.fooddeliveryapi.api.dto.RoleDTO;
import com.daniellsantiago.fooddeliveryapi.api.exceptionhandler.ExceptionDetails;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Users")
public interface UserRoleControllerOpenApi {

    @ApiOperation("Find all Roles associated with an User")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Role associated with this User registered"),
            @ApiResponse(code = 400, message = "Invalid User ID", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "User not found", response = ExceptionDetails.class)
    })
    ResponseEntity<List<RoleDTO>> findAll(@ApiParam(value = "User ID", example = "1", required = true)
                                          Long userId);

    @ApiOperation("Associate Role to User")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Association succeed", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "Role or User not found", response = ExceptionDetails.class)
    })
    ResponseEntity<Void> associate(@ApiParam(value = "User ID", example = "1", required = true)
                                   Long userId,
                                   @ApiParam(value = "Role ID", example = "1", required = true)
                                   Long roleId);

    @ApiOperation("Disassociate Role to User")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Disassociate succeed", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "Role or User not found", response = ExceptionDetails.class)
    })
    ResponseEntity<Void> disassociate(@ApiParam(value = "User ID", example = "1", required = true)
                                           Long userId,
                                   @ApiParam(value = "Role ID", example = "1", required = true)
                                           Long roleId);
}
