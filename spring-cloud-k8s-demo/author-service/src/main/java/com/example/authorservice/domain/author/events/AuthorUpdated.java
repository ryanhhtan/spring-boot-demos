package com.example.authorservice.domain.author.events;

import com.example.authorservice.domain.author.model.Author;

/**
 * AuthorUpdated
 */
public class AuthorUpdated extends DomainEvent<Author> {
  private static final long serialVersionUID = 1L;

  public AuthorUpdated(Author source) {
    super(source);
  }
}
