package com.example.springcloudcontractdemo.service;

import java.util.List;
import com.example.springcloudcontractdemo.model.Post;

/**
 * PostService
 */

public interface PostService {
  public List<Post> findAll();
  public Post findById(final Long id);
  public Post create(final Post post);
  public Post update(final Long id, final Post updatePost);
  public void deleteById(final Long id);
}
