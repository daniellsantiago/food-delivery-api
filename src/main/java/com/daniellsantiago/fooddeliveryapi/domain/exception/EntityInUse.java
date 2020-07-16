package com.daniellsantiago.fooddeliveryapi.domain.exception;

public class EntityInUse extends RuntimeException{
    public EntityInUse(String message) {
        super(message);
    }
}
