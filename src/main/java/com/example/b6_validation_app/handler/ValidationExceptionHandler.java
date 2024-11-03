package com.example.b6_validation_app.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.b6_validation_app.common.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.MethodArgumentNotValidException;

@ControllerAdvice
@Slf4j
public class ValidationExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult()
      .getAllErrors()
      .stream()
      .map(error -> error.getDefaultMessage())
      .collect(Collectors.toList());

    ErrorResponse errorResponse = ErrorResponse.builder()
      .message("Validation failed")
      .errors(errors)
      .build();

    log.error("Validation failed: {}", errors);
            
    return ResponseEntity
      .badRequest()
      .body(errorResponse);
  }
}
