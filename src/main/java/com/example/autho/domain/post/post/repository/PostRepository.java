package com.example.autho.domain.post.post.repository;

import com.example.autho.domain.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
