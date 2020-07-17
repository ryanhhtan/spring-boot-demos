package com.example.springgraphqldemo.query;

import java.util.List;
import java.util.Optional;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.springgraphqldemo.model.Author;
import com.example.springgraphqldemo.service.AuthorService;
import org.springframework.stereotype.Component;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * AuthorQuery
 */
@Component
@RequiredArgsConstructor
public class AuthorQuery implements GraphQLQueryResolver {

  @NonNull
  private AuthorService authorService;

  public List<Author> authors(final Integer count, final Long offset) {
    return authorService.findAll(count, offset);
  }

  public Optional<Author> author(final Long id) {
    return authorService.findByIdOptional(id);
  }
}
