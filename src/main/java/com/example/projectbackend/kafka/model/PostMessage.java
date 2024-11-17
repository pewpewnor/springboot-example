package com.example.projectbackend.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// NoArgsConstructor and AllArgsConstructor are needed for JsonDeserialization in Kafka

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostMessage {
  private Long authorId;
  private String message;
}
