package com.example.authorservice.domain.author.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
* UserNotFoundException
*/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthorNotFoundException extends RuntimeException{
	public AuthorNotFoundException(final String message) {
		super(message);
	}
  private static final long serialVersionUID = 1L;
}
