package com.example.springaopdemo.domain.post;

import java.util.List;
import java.util.Optional;

/**
 * PostService
 */

public interface PostService {
  public List<Post> findAll();
  public Post create(final Post post);
  public Optional<Post> findById(final Long id);
  public Post update(final Long id, final Post update) throws Exception;
  public void delete(final Long id) throws Exception;
}
