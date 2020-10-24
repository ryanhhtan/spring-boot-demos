package com.example.bookservice.common.event;

import java.time.Instant;
import org.springframework.context.ApplicationEvent;
import lombok.Getter;

/**
 * BaseEvent
 */

public abstract class BaseEvent extends ApplicationEvent {
  @Getter
  private Instant occuredAt;

  private static final long serialVersionUID = 1L;

  public BaseEvent(Object source) {
    super(source);
    this.occuredAt = Instant.now();
  }
}
