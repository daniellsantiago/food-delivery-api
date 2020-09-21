package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.openapi.controller.LoginOpenApi;
import com.daniellsantiago.fooddeliveryapi.core.security.jwt.UsernameAndPasswordAuthenticationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
public class FakeLoginController implements LoginOpenApi {

    @Override
    @PostMapping
    public UsernameAndPasswordAuthenticationRequest login(@RequestBody UsernameAndPasswordAuthenticationRequest userCredentials) {
        return null;
    }
}
