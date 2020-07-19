package com.daniellsantiago.fooddeliveryapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    INVALID_DATA("Invalid data"),
    SYSTEM_FAIL("System has failed"),
    INVALID_PARAMETER("Invalid parameter"),
    INCOMPREHENSIBLE_MESSAGE("Incomprehensible message"),
    RESOURCE_NOT_FOUND("Resource not Found"),
    ENTITY_IN_USE("Entity in use"),
    RULE_VIOLATION("Business rule violation");

    private String title;

    ProblemType(String title) {
        this.title = title;
    }
}
