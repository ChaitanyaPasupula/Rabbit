package com.rabbit.service;

import com.rabbit.model.AppUser;
import com.rabbit.repo.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

  private final AppUserRepository users;
  private final PasswordEncoder encoder;

  public AuthService(AppUserRepository users, PasswordEncoder encoder) {
    this.users = users;
    this.encoder = encoder;
  }

  public void signup(String username, String password) {
    if (username == null || username.isBlank()) throw new IllegalArgumentException("username required");
    if (password == null || password.length() < 6) throw new IllegalArgumentException("password must be 6+ chars");
    if (users.existsByUsername(username)) throw new IllegalArgumentException("username already exists");

    String hash = encoder.encode(password);
    users.save(new AppUser(username.trim(), hash));
  }

  public String loginAndIssueToken(String username, String password) {
    AppUser user = users.findByUsername(username)
        .orElseThrow(() -> new IllegalArgumentException("invalid credentials"));

    if (!encoder.matches(password, user.getPasswordHash())) {
      throw new IllegalArgumentException("invalid credentials");
    }

    return UUID.randomUUID().toString();
  }
}
