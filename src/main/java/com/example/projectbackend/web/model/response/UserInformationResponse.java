package com.example.projectbackend.web.model.response;

import com.example.projectbackend.entity.User;
import com.example.projectbackend.web.model.UserRole;
import lombok.Data;

@Data
public class UserInformationResponse {
  private final Long id;
  private final String username;
  private final UserRole role;

  public UserInformationResponse(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.role = user.getRole();
  }
}
