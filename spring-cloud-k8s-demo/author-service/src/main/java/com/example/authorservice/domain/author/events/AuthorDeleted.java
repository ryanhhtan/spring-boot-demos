package com.example.authorservice.domain.author.events;

import com.example.authorservice.domain.author.model.Author;
import com.example.authorservice.eventhandler.DomainEvent;

/**
 * AuthorDeleted
 */
public class AuthorDeleted extends DomainEvent<Author>{

  private static final long serialVersionUID = 1L;

  public AuthorDeleted(Author source) {
    super(source);
  }
}
