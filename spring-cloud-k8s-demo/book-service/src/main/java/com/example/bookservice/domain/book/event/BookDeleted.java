package com.example.bookservice.domain.book.event;

import com.example.bookservice.common.event.DomainEvent;
import com.example.bookservice.domain.book.model.Book;

/**
 * BookDeleted
 */

public class BookDeleted extends DomainEvent<Book> {

  private static final long serialVersionUID = 1L;

  public BookDeleted(Book source) {
    super(source);
  }
}
