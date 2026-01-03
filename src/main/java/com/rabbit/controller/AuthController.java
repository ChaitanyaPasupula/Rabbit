package com.rabbit.controller;

import com.rabbit.dto.AuthDtos;
import com.rabbit.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService auth;

  public AuthController(AuthService auth) {
    this.auth = auth;
  }

  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody AuthDtos.SignupRequest req) {
    auth.signup(req.username(), req.password());
    return ResponseEntity.ok().build();
  }

  @PostMapping("/login")
  public ResponseEntity<AuthDtos.TokenResponse> login(@RequestBody AuthDtos.LoginRequest req) {
    String token = auth.loginAndIssueToken(req.username(), req.password());
    return ResponseEntity.ok(new AuthDtos.TokenResponse(token));
  }
}
