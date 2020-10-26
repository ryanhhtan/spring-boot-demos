package com.example.bookservice.common.event;

import java.time.Instant;
import lombok.Data;

/**
 * OutGoingMessage
 */
@Data
public class OutGoingMessage {
  private String event;
  private Instant occuredAt;
  private String id;

  public OutGoingMessage(DomainEvent<? extends Domain> event) {
    this.event = event.getClass().getSimpleName();
    this.occuredAt = event.getOccuredAt();
    this.id = ((Domain) event.getSource()).getId().toString();
  }
}
