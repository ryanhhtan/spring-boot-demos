package com.example.springgraphqldemo.mutation;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.springgraphqldemo.model.Author;
import com.example.springgraphqldemo.service.AuthorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * AuthorMutation
 */
@Component
@RequiredArgsConstructor
public class AuthorMutation implements GraphQLMutationResolver{
  @NonNull
  private AuthorService authorService;

  @PreAuthorize("isAuthenticated()")
  public Author updateAuthor(final String name, final String thumbnail) {
    return authorService.updateAuthor(Author.builder().name(name).thumbnail(thumbnail).build());
  }
}
