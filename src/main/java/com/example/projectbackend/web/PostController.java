package com.example.projectbackend.web;

import com.example.projectbackend.entity.User;
import com.example.projectbackend.helper.AuthHelper;
import com.example.projectbackend.kafka.model.PostMessage;
import com.example.projectbackend.service.AuthService;
import com.example.projectbackend.web.model.request.CreateOrUpdatePostRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
@RequiredArgsConstructor
public class PostController extends BaseController {
  private final KafkaTemplate<String, PostMessage> kafkaTemplate;
  private final AuthService authService;

  @PostMapping("/post")
  public ResponseEntity<String> createPost(
      @RequestHeader HttpHeaders headers,
      @RequestBody @Valid CreateOrUpdatePostRequest createPostRequest) {
    User user = authService.authenticate(AuthHelper.getBearerTokenFromHeaders(headers));
    kafkaTemplate.send("posts", new PostMessage(user.getId(), createPostRequest.getMessage()));
    return ResponseEntity.ok("success");
  }
}
