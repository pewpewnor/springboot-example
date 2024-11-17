package com.example.projectbackend.service.impl;

import com.example.projectbackend.entity.mongodb.Post;
import com.example.projectbackend.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

  private final MongoTemplate mongoTemplate;

  @Override
  public Mono<Post> createPost(Long authorId, String message) {
    return Mono.just(new Post(authorId, message)).map(post -> mongoTemplate.insert(post, "posts"));
  }
}
