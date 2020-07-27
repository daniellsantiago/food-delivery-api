package com.daniellsantiago.fooddeliveryapi.domain.exception;

public class BussinessRuleException extends RuntimeException{
    public BussinessRuleException(String message) {
        super(message);
    }
}
