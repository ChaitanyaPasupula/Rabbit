package com.rabbit.controller;

import com.rabbit.dto.PostDtos;
import com.rabbit.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

  private final PostService posts;

  public PostController(PostService posts) {
    this.posts = posts;
  }

  // MVP auth: pass username in header. Later we’ll replace with JWT auth.
  @PostMapping
  public ResponseEntity<PostDtos.PostResponse> create(
      @RequestHeader("X-USER") String username,
      @RequestBody PostDtos.CreatePostRequest req
  ) {
    return ResponseEntity.ok(posts.createPost(username, req.content()));
  }

  @GetMapping("/feed")
  public ResponseEntity<List<PostDtos.PostResponse>> feed() {
    return ResponseEntity.ok(posts.feed());
  }
}
