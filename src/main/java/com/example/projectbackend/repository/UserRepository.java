package com.example.projectbackend.repository;

import com.example.projectbackend.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByToken(String token);

  Optional<User> findByUsernameAndPassword(String username, String password);
}
