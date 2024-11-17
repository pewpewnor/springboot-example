package com.example.projectbackend.web.model;

import com.example.projectbackend.exception.http.BadRequestException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserRole {
  CUSTOMER,
  ADMIN;

  public static UserRole from(String role) {
    return switch (role) {
      case "CUSTOMER" -> UserRole.CUSTOMER;
      case "ADMIN" -> UserRole.ADMIN;
      default -> {
        throw new BadRequestException("Given user role is invalid");
      }
    };
  }
}
