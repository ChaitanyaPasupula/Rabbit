package com.rabbit.dto;

public class AuthDtos {
  public record SignupRequest(String username, String password) {}
  public record LoginRequest(String username, String password) {}
  public record TokenResponse(String token) {}
}
