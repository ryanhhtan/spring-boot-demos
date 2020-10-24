package com.example.bookservice.domain.book.event;

import java.util.function.Consumer;
import com.example.bookservice.common.event.OutGoingMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

/**
 * AuthorDeletedHandler
 */
@Configuration
@Slf4j
public class AuthorDeletedHandler {

  @Bean
  public Consumer<OutGoingMessage> handleAuthorEvent() {
    return message -> log.info("*** received message: {}", message);
  }
}
