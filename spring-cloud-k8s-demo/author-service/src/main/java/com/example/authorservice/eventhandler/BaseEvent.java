package com.example.authorservice.eventhandler;

import java.time.Instant;
import org.springframework.context.ApplicationEvent;
import lombok.Getter;

/**
 * BaseEvent
 */
public abstract class BaseEvent <T>  extends ApplicationEvent{

  private static final long serialVersionUID = 1L;

  @Getter
  private Instant occuredAt;

  public BaseEvent(Object source) {
    super(source);
    occuredAt = Instant.now();
  }
  
}
