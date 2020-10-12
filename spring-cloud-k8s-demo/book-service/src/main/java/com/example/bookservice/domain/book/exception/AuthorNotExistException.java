package com.example.bookservice.domain.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
* AuthorNotExistException
*/
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AuthorNotExistException extends IllegalArgumentException {

  private static final long serialVersionUID = 1L;

  public AuthorNotExistException(String s) {
    super(s);
  }
}
