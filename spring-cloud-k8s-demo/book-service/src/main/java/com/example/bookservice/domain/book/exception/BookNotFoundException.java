package com.example.bookservice.domain.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
* BookNotFoundException
*/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public BookNotFoundException() {
		super("Book not found");
  }

  public BookNotFoundException(String message) {
    super(message);
  }
	
}
