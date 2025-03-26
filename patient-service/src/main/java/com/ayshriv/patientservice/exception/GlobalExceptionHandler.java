package com.ayshriv.patientservice.exception;

import com.ayshriv.patientservice.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handle MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        LOGGER.error("Validation failed - {}", ex.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed");
        Map<String, String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (existing, replacement) -> existing));
        problemDetail.setProperty("status_type", Constants.STATUS_FAILURE);
        problemDetail.setProperty("errors", errors);
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    // Handle EmailAlreadyExistsException
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ProblemDetail handleEmailAlreadyExistsException(EmailAlreadyExistsException ex)
    {
        LOGGER.error("Email already exists - {}", ex.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("status_type", Constants.STATUS_FAILURE);
        return problemDetail;
    }

    // Handle ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex) {
        LOGGER.error("Resource not found - {}", ex.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("status_type", Constants.STATUS_FAILURE);
        return problemDetail;
    }

    // Handle Generic Exceptions
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex) {
        LOGGER.error("An unexpected error occurred: {}", ex.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred: " + ex.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("status_type", Constants.STATUS_ERROR);
        return problemDetail;
    }

    // Handle EmailAlreadyExistsException
    @ExceptionHandler(PhoneNumberAlreadyExistsException.class)
    public ProblemDetail handlePhoneNumberAlreadyExistsException(PhoneNumberAlreadyExistsException ex)
    {
        LOGGER.error("Phone number already exists - {}", ex.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("status_type", Constants.STATUS_FAILURE);
        return problemDetail;
    }


}
