package com.example.springgraphqldemo.query;

import java.util.List;
import java.util.Optional;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.springgraphqldemo.model.Post;
import com.example.springgraphqldemo.service.PostService;
import org.springframework.stereotype.Component;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * PostQuery
 */
@Component
@RequiredArgsConstructor
public class PostQuery implements GraphQLQueryResolver {

  @NonNull
  private PostService postService;

  public List<Post> posts(final Integer count, final Long offset) {
    return postService.findAll(count, offset);
  }

  public Optional<Post> post(final Long id) {
    return postService.findByIdOptional(id);
  }
}
