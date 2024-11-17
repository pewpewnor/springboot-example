package com.example.projectbackend.web.exceptionhandler;

import com.example.projectbackend.web.model.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class RestExceptionHandler {
  // since NotFoundException extends ResponseStatusException, this will handle that custom Exception
  // as well
  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException e) {
    return new ResponseEntity<>(
        new ErrorResponse(e.getReason(), e.getStatusCode().toString()), e.getStatusCode());
  }
}
