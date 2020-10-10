package com.example.bookservice.book.exdata.resolver;

import java.util.Collection;
import com.example.bookservice.book.exdata.model.Author;

/**
 * AuthorResolver
 */
public interface AuthorResolver {
  public Author findById(final Long id);
  public Collection<Author> findById(final Collection<Long> ids);
}
