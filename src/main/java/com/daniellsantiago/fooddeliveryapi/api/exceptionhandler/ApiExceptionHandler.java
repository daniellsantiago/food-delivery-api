package com.daniellsantiago.fooddeliveryapi.api.exceptionhandler;

import com.daniellsantiago.fooddeliveryapi.domain.exception.EntityInUseException;
import com.daniellsantiago.fooddeliveryapi.domain.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String MSG_GENERIC_ERROR = "An unexpected internal system error has occurred. " +
            "try again and if the problem persists, contact your system administrator.";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
        String detail = ex.getMessage();

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .timestamp(OffsetDateTime.now(ZoneOffset.UTC))
                .status(status.value())
                .title(problemType.getTitle())
                .detail(detail)
                .userMessage(detail).build();

        return handleExceptionInternal(ex, exceptionDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handleEntityInUse(EntityInUseException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTITY_IN_USE;
        String detail = ex.getMessage();

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .timestamp(OffsetDateTime.now(ZoneOffset.UTC))
                .status(status.value())
                .title(problemType.getTitle())
                .detail(detail)
                .userMessage(detail)
                .build();
        return handleExceptionInternal(ex, exceptionDetails, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> fieldMessage = fieldErrors.stream()
                .map((field) -> field.getField() + " " + field.getDefaultMessage()).collect(Collectors.toList());
        ProblemType problemType = ProblemType.INVALID_DATA;
        String details = fieldMessage.toString();
        String userMessage = "One or more fields are invalid. Fill in correctly and try again.";
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .timestamp(OffsetDateTime.now(ZoneOffset.UTC))
                .status(status.value())
                .title(problemType.getTitle())
                .detail(details)
                .userMessage(userMessage)
                .build();
        return handleExceptionInternal(ex, exceptionDetails, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;

        String detail = String.format("Resource %s, that you tried to access, not exists.",
                ex.getRequestURL());

        ExceptionDetails problem = ExceptionDetails.builder()
                .timestamp(OffsetDateTime.now(ZoneOffset.UTC))
                .status(status.value())
                .title(problemType.getTitle())
                .detail(detail)
                .userMessage(MSG_GENERIC_ERROR)
                .build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch(
                    (MethodArgumentTypeMismatchException) ex, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.INVALID_PARAMETER;

        String detail = String.format("The url parameter '%s' received the value '%s', "
                        + "which is not valid. Enter a value compatible with the type %s.",
                ex.getName(), ex.getValue(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName());

        ExceptionDetails problem = ExceptionDetails.builder()
                .timestamp(OffsetDateTime.now(ZoneOffset.UTC))
                .status(status.value())
                .title(problemType.getTitle())
                .detail(detail)
                .userMessage(MSG_GENERIC_ERROR)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
        }

        ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
        String detail = "The request body is invalid. Verify the sintax error.";

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .timestamp(OffsetDateTime.now(ZoneOffset.UTC))
                .status(status.value())
                .title(problemType.getTitle())
                .detail(detail)
                .userMessage(MSG_GENERIC_ERROR)
                .build();

        return handleExceptionInternal(ex, exceptionDetails, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.SYSTEM_FAIL;
        String detail = MSG_GENERIC_ERROR;

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
            .timestamp(OffsetDateTime.now(ZoneOffset.UTC))
            .status(status.value())
            .title(problemType.getTitle())
            .detail(detail)
            .userMessage(detail)
            .build();

        return handleExceptionInternal(ex, exceptionDetails, new HttpHeaders(), status, request);
    }
    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex,
                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = joinPath(ex.getPath());

        ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
        String detail = String.format("Property '%s' does not exists. "
                + "Please, enter a valid property.", path);

        ExceptionDetails problem = ExceptionDetails.builder()
                .timestamp(OffsetDateTime.now(ZoneOffset.UTC))
                .status(status.value())
                .title(problemType.getTitle())
                .detail(detail)
                .userMessage(MSG_GENERIC_ERROR)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex,
                                                       HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = joinPath(ex.getPath());

        ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
        String detail = String.format("Property '%s' received the value '%s', "
                        + "which is not valid. Please, enter a value compatible with the type %s.",
                path, ex.getValue(), ex.getTargetType().getSimpleName());

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .timestamp(OffsetDateTime.now(ZoneOffset.UTC))
                .status(status.value())
                .title(problemType.getTitle())
                .detail(detail)
                .userMessage(MSG_GENERIC_ERROR)
                .build();

        return handleExceptionInternal(ex, exceptionDetails, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        if (body == null) {
            body = ExceptionDetails.builder()
                    .timestamp(OffsetDateTime.now(ZoneOffset.UTC))
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .userMessage(MSG_GENERIC_ERROR)
                    .build();
        } else if (body instanceof String) {
            body = ExceptionDetails.builder()
                    .timestamp(OffsetDateTime.now())
                    .title((String) body)
                    .status(status.value())
                    .userMessage(MSG_GENERIC_ERROR)
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private String joinPath(List<Reference> references) {
        return references.stream()
                .map(Reference::getFieldName)
                .collect(Collectors.joining("."));
    }
}
