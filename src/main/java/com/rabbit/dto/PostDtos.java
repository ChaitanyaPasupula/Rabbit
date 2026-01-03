package com.rabbit.dto;

import java.time.Instant;

public class PostDtos {
  public record CreatePostRequest(String content) {}
  public record PostResponse(Long id, String username, String content, Instant createdAt) {}
}
