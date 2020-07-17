package com.example.springgraphqldemo.resolver;

import java.util.List;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.springgraphqldemo.model.Author;
import com.example.springgraphqldemo.model.Post;
import com.example.springgraphqldemo.service.PostService;
import org.springframework.stereotype.Component;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * AuthorResolver
 */
@Component
@RequiredArgsConstructor
public class AuthorResolver implements GraphQLResolver<Author> {

  @NonNull
  private PostService postService;

  public List<Post> getPosts(final Author author) {
    return postService.findByAuthor(author);
  }
}
