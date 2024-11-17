package com.example.projectbackend.web.exceptionhandler;

import com.example.projectbackend.web.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RuntimeExceptionHandler {
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
    return new ResponseEntity<>(
        new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
