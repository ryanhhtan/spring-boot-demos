package com.example.springgraphqldemo.repository;

import java.util.Optional;
import com.example.springgraphqldemo.model.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * AuthorRepository
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
  Optional<Author> findByUsername(final String username);
  @EntityGraph(attributePaths = "posts")
  Optional<Author> findWithPostsByUsername(final String username);
}
