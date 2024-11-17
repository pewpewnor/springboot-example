package com.example.projectbackend.kafka;

import com.example.projectbackend.kafka.model.PostMessage;
import com.example.projectbackend.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "posts", groupId = "foo")
@RequiredArgsConstructor
@Slf4j
public class PostsTopicListener {
  private final PostService postService;

  // can also just be @KafkaListener in the individual methods themselves

  @KafkaHandler
  public void postsListener(PostMessage postMessage) {
    log.info("Received from Kafka: {}", postMessage);
    postService
        .createPost(postMessage.getAuthorId(), postMessage.getMessage())
        .subscribe(
            post -> {
              log.info("Created new post: {}", post);
            });
  }
}
