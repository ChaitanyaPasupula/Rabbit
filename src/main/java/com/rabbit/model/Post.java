package com.rabbit.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "post")
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private AppUser user;

  @Column(nullable = false, length = 280)
  private String content;

  @Column(nullable = false)
  private Instant createdAt;

  protected Post() {}

  public Post(AppUser user, String content, Instant createdAt) {
    this.user = user;
    this.content = content;
    this.createdAt = createdAt;
  }

  public Long getId() { return id; }
  public AppUser getUser() { return user; }
  public String getContent() { return content; }
  public Instant getCreatedAt() { return createdAt; }
}
