package com.rabbit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  @GetMapping("/api")
  public String home() {
    return "Rabbit API is running. Try /actuator/health or /api/posts/feed";
  }
}
