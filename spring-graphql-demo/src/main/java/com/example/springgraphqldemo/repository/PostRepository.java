package com.example.springgraphqldemo.repository;

import java.util.List;
import com.example.springgraphqldemo.model.Author;
import com.example.springgraphqldemo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PostRepository
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  public List<Post> findByAuthor(final Author author);
}
