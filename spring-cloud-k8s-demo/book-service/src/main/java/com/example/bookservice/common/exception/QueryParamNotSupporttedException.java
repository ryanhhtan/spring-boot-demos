package com.example.bookservice.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * QueryParamNotSupportException
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QueryParamNotSupporttedException extends IllegalArgumentException {

  private static final long serialVersionUID = 1L;

  public QueryParamNotSupporttedException(final String s) {
    super(s);
  }
}
