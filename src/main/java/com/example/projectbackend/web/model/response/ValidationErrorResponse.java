package com.example.projectbackend.web.model.response;

import java.util.List;
import lombok.Data;

@Data
public class ValidationErrorResponse {
  private final String message;
  private final List<Validation> validations;
}
