package com.example.authorservice.domain.author.events;

import com.example.authorservice.domain.author.model.Author;
import com.example.authorservice.eventhandler.DomainEvent;

/**
 * AuthorCreatedEvent
 */
public class AuthorCreated extends DomainEvent<Author> {

  private static final long serialVersionUID = 1L;

  public AuthorCreated(Author source) {
    super(source);
  }
}
