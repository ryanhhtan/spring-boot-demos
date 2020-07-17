package com.example.springgraphqldemo.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.springgraphqldemo.model.Post;
import com.example.springgraphqldemo.service.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * PostMutation
 */
@Component
@RequiredArgsConstructor
public class PostMutation implements GraphQLMutationResolver {

  @NonNull
  private PostService postService;

  @PreAuthorize("isAuthenticated()")
  public Post createPost(final String title, final String text, final String category) {
    return postService.createPost(Post.builder().title(title).text(text).category(category).build());
  }

  @PreAuthorize("isAuthenticated()")
  public Post updatePost(final Long id, final String title, final String text, final String category) {
    return postService.updatePost(id, Post.builder().title(title).text(text).category(category).build());
  }
}
