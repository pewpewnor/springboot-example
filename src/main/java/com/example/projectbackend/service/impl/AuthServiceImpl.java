package com.example.projectbackend.service.impl;

import com.example.projectbackend.entity.User;
import com.example.projectbackend.exception.http.ForbiddenException;
import com.example.projectbackend.exception.http.NotFoundException;
import com.example.projectbackend.helper.TokenGeneratorHelper;
import com.example.projectbackend.repository.UserRepository;
import com.example.projectbackend.service.AuthService;
import com.example.projectbackend.service.UserService;
import com.example.projectbackend.web.model.UserRole;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
  private final UserRepository userRepository;
  private final UserService userService;

  @Override
  public User login(String username, String password) {
    Optional<User> optionalUser = userRepository.findByUsernameAndPassword(username, password);
    if (optionalUser.isEmpty()) {
      throw new NotFoundException("Login credential is incorrect");
    }
    return updateUserWithNewToken(optionalUser.get());
  }

  @Override
  public User register(String username, String password) {
    return userService.createUser(username, password, UserRole.CUSTOMER);
  }

  /*
    Note:
      In @Cachable kye and value params are SpEL, the # refers to actual parameter / variable names
      and can even access attributes and call methods in the string like a normal Java expression
      there is also unless to determine what condition will it be cached
  */

  @Override
  @Cacheable(value = "tokens", key = "#authorizationToken")
  public User authenticate(String authorizationToken) {
    log.info("method authenticate " + authorizationToken + " cache miss");
    Optional<User> user = userRepository.findByToken(authorizationToken);
    if (user.isEmpty()) {
      throw new ForbiddenException("Header's bearer token is incorrect");
    }
    return user.get();
  }

  @Override
  public User authenticateAdmin(String authorizationToken) {
    User user = authenticate(authorizationToken);
    if (user.getRole() != UserRole.ADMIN) {
      throw new ForbiddenException("Admin permission is required to make this request");
    }
    return user;
  }

  @CacheEvict(value = "tokens", key = "#user.getToken()")
  private User updateUserWithNewToken(User user) {
    user.setToken(TokenGeneratorHelper.generateToken());
    return userRepository.save(user);
  }
}
