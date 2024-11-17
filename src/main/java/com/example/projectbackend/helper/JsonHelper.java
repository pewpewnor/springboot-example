package com.example.projectbackend.helper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {
  private static ObjectMapper objectMapper = new ObjectMapper();

  public static ObjectMapper getOjectMapper() {
    return objectMapper;
  }
}
