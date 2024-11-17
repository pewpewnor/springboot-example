package com.example.projectbackend.service;

import com.example.projectbackend.entity.User;

public interface AuthService {
  User login(String username, String password);

  User register(String username, String password);

  User authenticate(String authorizationToken);

  User authenticateAdmin(String authorizationToken);
}
