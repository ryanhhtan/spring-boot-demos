package com.example.authorservice.author.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
* QueryParamNotSupportedException
*/
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QueryParamNotSupportedException extends IllegalArgumentException {
  private static final long serialVersionUID = 1L;

  public QueryParamNotSupportedException(String s) {
    super(s);
  }
}
