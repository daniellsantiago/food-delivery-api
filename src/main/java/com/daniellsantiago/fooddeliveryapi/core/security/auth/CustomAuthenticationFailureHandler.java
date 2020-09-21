package com.daniellsantiago.fooddeliveryapi.core.security.auth;

import com.daniellsantiago.fooddeliveryapi.api.exceptionhandler.ExceptionDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.OffsetDateTime;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper mapper;

    @Override
    @SneakyThrows
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException e) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("BAD REQUEST")
                .status(HttpStatus.UNAUTHORIZED.value())
                .timestamp(OffsetDateTime.now())
                .userMessage("Username or Password invalid")
                .detail(e.getMessage()).build();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(mapper.writeValueAsString(exceptionDetails));
    }
}
