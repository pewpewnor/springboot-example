package com.example.projectbackend;

import com.example.projectbackend.entity.User;
import com.example.projectbackend.repository.UserRepository;
import com.example.projectbackend.web.model.UserRole;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SpringBootEventListeners {
  private final UserRepository userRepository;

  /*
    Note:
    - ContextRefreshEvent for right before server is started but after context and component has been initialized
    - ApplicationReadyEvent for right after the server is started
  */

  @EventListener(ContextRefreshedEvent.class)
  private void rightBeforeServerStartsAndAfterContextIsReady() {
    userRepository.save(new User("admin", "admin", UserRole.ADMIN, "admin_token"));
    log.info("Database has been filled with dummy data");
  }

  @EventListener(ApplicationReadyEvent.class)
  private void afterServerStarts() {
    Map<Object, Object> map = new HashMap<>();
    map.put(
        "result",
        "<html>  <head><title>400 The plain HTTP request was sent to HTTPS port</title></head> "
            + " <body>  <center><h1>400 Bad Request</h1></center>  <center>The plain HTTP request"
            + " was sent to HTTPS port</center>  </body>  </html> ");
    log.info("[{}]", map);
  }
}
