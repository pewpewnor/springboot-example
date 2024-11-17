package com.example.projectbackend.web;

import com.example.projectbackend.entity.User;
import com.example.projectbackend.service.AuthService;
import com.example.projectbackend.web.model.request.LoginOrRegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AuthController extends BaseController {
  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<User> login(@RequestBody @Valid LoginOrRegisterRequest loginRequest) {
    User user = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
    return ResponseEntity.ok(user);
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody @Valid LoginOrRegisterRequest registerRequest) {
    User user = authService.register(registerRequest.getUsername(), registerRequest.getPassword());
    return ResponseEntity.ok(user);
  }
}
