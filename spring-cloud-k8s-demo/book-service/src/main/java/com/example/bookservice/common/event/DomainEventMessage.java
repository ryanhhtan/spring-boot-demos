package com.example.bookservice.common.event;

import java.time.Instant;
import lombok.Data;

/**
 * OutGoingMessage
 */
@Data
public class DomainEventMessage {
  private String event;
  private Instant occuredAt;
  private String id;
  
  private DomainEventMessage(){}

  public DomainEventMessage(DomainEvent<? extends Domain> event) {
    this();
    this.event = event.getClass().getSimpleName();
    this.occuredAt = event.getOccuredAt();
    this.id = ((Domain) event.getSource()).getId().toString();
  }
}
