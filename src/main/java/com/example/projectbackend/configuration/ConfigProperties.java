package com.example.projectbackend.configuration;

import java.util.List;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ConfigProperties {
  @Value("${kafka.bootstrap-servers}")
  private String bootstrapAddress;

  @Value("${kafka.new-topics}")
  private List<String> newTopicNames;

  @Value("${mongodb.url}")
  private String mongodbUrl;

  @Value("${mongodb.database-name}")
  private String mongodbDatabaseName;
}
