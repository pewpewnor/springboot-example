package com.example.projectbackend.web.model.response;

import lombok.Data;

@Data
public class ErrorResponse {
  private final String reason;
  private final String statusCode;
}
