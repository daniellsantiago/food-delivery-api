package com.daniellsantiago.fooddeliveryapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
@Getter
@Setter
public class ExceptionDetails {
    private Integer status;
    private OffsetDateTime timestamp;
    private String title;
    private String detail;
    private String userMessage;
}
