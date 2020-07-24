package com.daniellsantiago.fooddeliveryapi.domain.exception;

public class InvalidDataRequestException extends RuntimeException{

    public InvalidDataRequestException(String message) {
        super(message);
    }
}
