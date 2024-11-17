package com.example.projectbackend.service;

import com.example.projectbackend.entity.User;
import com.example.projectbackend.web.model.UserRole;
import java.util.List;

public interface UserService {
  User getUserById(Long id);

  List<User> getAllUsers();

  User createUser(String username, String password, UserRole role);

  void updateUser(long id, String username, String password);

  void deleteUserById(Long id);
}
