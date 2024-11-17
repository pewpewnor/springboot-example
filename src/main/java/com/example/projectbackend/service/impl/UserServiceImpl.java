package com.example.projectbackend.service.impl;

import com.example.projectbackend.entity.User;
import com.example.projectbackend.exception.http.NotFoundException;
import com.example.projectbackend.helper.TokenGeneratorHelper;
import com.example.projectbackend.repository.UserRepository;
import com.example.projectbackend.service.UserService;
import com.example.projectbackend.web.model.UserRole;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Override
  public User getUserById(Long id) {
    Optional<User> optionalUser = userRepository.findById(id);
    if (optionalUser.isEmpty()) {
      throw new NotFoundException("User with given id does not exist");
    }
    return optionalUser.get();
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User createUser(String username, String password, UserRole role) {
    User user = new User(username, password, role, TokenGeneratorHelper.generateToken());
    return userRepository.saveAndFlush(user);
  }

  @Override
  public void updateUser(long id, String username, String password) {
    User user = getUserById(id);
    user.setUsername(username);
    user.setPassword(password);
    userRepository.save(user);
  }

  @Override
  public void deleteUserById(Long id) {
    userRepository.deleteById(id);
  }
}
