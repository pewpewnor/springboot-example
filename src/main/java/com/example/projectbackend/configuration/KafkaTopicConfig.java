package com.example.projectbackend.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaAdmin.NewTopics;

@Configuration
@RequiredArgsConstructor
public class KafkaTopicConfig {
  private final ConfigProperties configProperties;

  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, configProperties.getBootstrapAddress());
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopics posts() {
    List<NewTopic> topics =
        configProperties.getNewTopicNames().stream()
            .map(topicName -> new NewTopic(topicName, 1, (short) 1))
            .toList();
    return new NewTopics(topics.toArray(new NewTopic[topics.size()]));
  }
}
