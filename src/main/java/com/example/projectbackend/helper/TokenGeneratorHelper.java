package com.example.projectbackend.helper;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenGeneratorHelper {
  private static final SecureRandom secureRandom = new SecureRandom();
  private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

  public static String generateToken() {
    return generateTokenWithLength(64);
  }

  public static String generateTokenWithLength(int length) {
    /*
      Generate random string in base64 encoding with 32 chars.
      In Base64 encoding every char encodes 6 bits of the data.
      So for 24 bytes from the above example you get the 32 chars.
      stringLength = byteArrayLength * 8 / 6
      therefore byteArrayLength = stringLength * 6 / 8
    */
    byte[] randomBytes = new byte[length * 6 / 8];
    secureRandom.nextBytes(randomBytes);
    return base64Encoder.encodeToString(randomBytes);
  }
}
