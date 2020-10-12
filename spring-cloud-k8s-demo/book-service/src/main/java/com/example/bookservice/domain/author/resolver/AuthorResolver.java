package com.example.bookservice.domain.author.resolver;


import java.util.Collection;
import com.example.bookservice.domain.author.model.Author;

/**
 * AuthorResolver
 */
public interface AuthorResolver {
  public Author findById(final Long id);
  public Collection<Author> findById(final Collection<Long> ids);
}
