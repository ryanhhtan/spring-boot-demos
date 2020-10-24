package com.example.bookservice.domain.book.event;

import com.example.bookservice.common.event.DomainEvent;
import com.example.bookservice.domain.book.model.Book;

/**
 * BookUpdated
 */
public class BookUpdated extends DomainEvent<Book> {

  private static final long serialVersionUID = 1L;

  public BookUpdated(Book source) {
    super(source);
  }
}
