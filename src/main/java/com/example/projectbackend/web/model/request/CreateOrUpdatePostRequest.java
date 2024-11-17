package com.example.projectbackend.web.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateOrUpdatePostRequest {
  @NotBlank private String message;
}
