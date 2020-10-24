package com.example.bookservice.domain.book.event;

import com.example.bookservice.common.event.DomainEvent;
import com.example.bookservice.domain.book.model.Book;

/**
 * BookCreated
 */
public class BookCreated extends DomainEvent<Book> {

  private static final long serialVersionUID = 1L;

  public BookCreated(Book source) {
    super(source);
  }
}
