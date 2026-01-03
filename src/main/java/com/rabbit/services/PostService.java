package com.rabbit.service;

import com.rabbit.dto.PostDtos;
import com.rabbit.model.AppUser;
import com.rabbit.model.Post;
import com.rabbit.repo.AppUserRepository;
import com.rabbit.repo.PostRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PostService {

  private final AppUserRepository users;
  private final PostRepository posts;

  public PostService(AppUserRepository users, PostRepository posts) {
    this.users = users;
    this.posts = posts;
  }

  public PostDtos.PostResponse createPost(String username, String content) {
    if (content == null || content.isBlank()) throw new IllegalArgumentException("content required");
    if (content.length() > 280) throw new IllegalArgumentException("content max 280 chars");

    AppUser user = users.findByUsername(username)
        .orElseThrow(() -> new IllegalArgumentException("user not found"));

    Post saved = posts.save(new Post(user, content.trim(), Instant.now()));
    return new PostDtos.PostResponse(saved.getId(), user.getUsername(), saved.getContent(), saved.getCreatedAt());
  }

  public List<PostDtos.PostResponse> feed() {
    return posts.findLatest().stream()
        .map(p -> new PostDtos.PostResponse(p.getId(), p.getUser().getUsername(), p.getContent(), p.getCreatedAt()))
        .toList();
  }
}
