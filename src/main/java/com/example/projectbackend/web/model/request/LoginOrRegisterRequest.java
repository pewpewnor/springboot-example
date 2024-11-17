package com.example.projectbackend.web.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginOrRegisterRequest {
  @NotBlank private final String username;
  @NotBlank private final String password;
}
