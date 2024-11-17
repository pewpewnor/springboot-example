package com.example.projectbackend.web.model.response;

import lombok.Data;

@Data
public class Validation {
  private final String field;
  private final String error;
}
