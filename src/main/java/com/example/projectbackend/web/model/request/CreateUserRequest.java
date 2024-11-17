package com.example.projectbackend.web.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserRequest {
  @NotBlank private String username;
  @NotBlank private String password;
  @NotBlank private String role;
}
