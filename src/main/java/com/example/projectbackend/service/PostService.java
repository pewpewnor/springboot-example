package com.example.projectbackend.service;

import com.example.projectbackend.entity.mongodb.Post;
import reactor.core.publisher.Mono;

public interface PostService {
  Mono<Post> createPost(Long authorId, String message);
}
