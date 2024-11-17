package com.example.projectbackend.helper;

import com.example.projectbackend.exception.http.UnauthorizedException;
import org.springframework.http.HttpHeaders;
import org.springframework.util.CollectionUtils;

public class AuthHelper {
  public static String getBearerTokenFromHeaders(HttpHeaders headers) throws UnauthorizedException {
    String authorization = CollectionUtils.lastElement(headers.get("Authorization"));
    if (authorization == null) {
      throw new UnauthorizedException("header's bearer token is required");
    }
    String[] authorizationTokens = authorization.split(" ");
    if (authorizationTokens.length != 2 || !authorizationTokens[0].equals("Bearer")) {
      throw new UnauthorizedException("Header's bearer token is invalid");
    }
    return authorizationTokens[1];
  }
}
