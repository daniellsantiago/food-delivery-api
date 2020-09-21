package com.daniellsantiago.fooddeliveryapi.api.openapi.controller;

import com.daniellsantiago.fooddeliveryapi.core.security.jwt.UsernameAndPasswordAuthenticationRequest;
import io.swagger.annotations.*;

@Api(tags = "Login")
public interface LoginOpenApi {

    @ApiOperation("Login and receive TOKEN")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Login successful"),
            @ApiResponse(code = 401, message = "Incorrect Credentials"),
    })
    UsernameAndPasswordAuthenticationRequest login(@ApiParam(name = "body", value = "Represents User credentials", required = true)
                                                           UsernameAndPasswordAuthenticationRequest userCredentials);
}
