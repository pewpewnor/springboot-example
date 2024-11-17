package com.example.projectbackend.web.exceptionhandler;

import com.example.projectbackend.web.model.response.Validation;
import com.example.projectbackend.web.model.response.ValidationErrorResponse;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ValidationExceptionHandler {
  // handle @Valid for validations in @ResponseBody
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationErrorResponse> handleValidationException(
      MethodArgumentNotValidException e) {
    List<Validation> validations =
        e.getBindingResult().getFieldErrors().stream()
            .map(
                fieldError -> new Validation(fieldError.getField(), fieldError.getDefaultMessage()))
            .toList();

    return new ResponseEntity<>(
        new ValidationErrorResponse("Validation error (method argument)", validations),
        HttpStatus.BAD_REQUEST);
  }

  // handle inline validation in controller methods
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ValidationErrorResponse> handleValidationException(
      ConstraintViolationException e) {
    List<Validation> validations =
        e.getConstraintViolations().stream()
            .map(
                constraintViolation ->
                    new Validation(
                        constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getMessage()))
            .toList();

    return new ResponseEntity<>(
        new ValidationErrorResponse("Validation error (constraint violation)", validations),
        HttpStatus.BAD_REQUEST);
  }
}
