package com.rabbit.repo;

import com.rabbit.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

  @Query("select p from Post p join fetch p.user u order by p.createdAt desc")
  List<Post> findLatest();
}
