package com.example.bookservice.beans;

import com.example.bookservice.domain.book.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

/**
 * CmdRunner
 */
@Component
@RequiredArgsConstructor
public class CmdRunner implements CommandLineRunner {

  private final BookService bookService;

  @Override
  public void run(String... args) throws Exception {
    bookService.removeInvalidAuthorFromBooks(18L);
  }
}
