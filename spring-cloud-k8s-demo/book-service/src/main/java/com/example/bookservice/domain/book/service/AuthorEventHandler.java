package com.example.bookservice.domain.book.service;

import java.util.function.Consumer;
import com.example.bookservice.common.event.DomainEventMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AuthorEventHandler
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorEventHandler {
  private final BookService bookService;

  @Bean
  public Consumer<DomainEventMessage> handleAuthorEvent() {
    return message -> {
      log.info("*** received message: {}", message);
      if (message.getEvent().equals("AuthorDeleted")) {
        final Long authorId = Long.valueOf(message.getId());
        bookService.removeInvalidAuthorFromBooks(authorId);
      }
    };
  }
}
