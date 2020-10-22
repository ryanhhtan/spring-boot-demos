package com.example.authorservice.eventhandler;

import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

/**
 * DomainRecord
 */
@Entity
@Getter
@Table(name = "event_records")
public class EventRecord {
  private static final ObjectMapper mapper = new ObjectMapper();

  EventRecord(){}
  public EventRecord(BaseEvent<?> event) {
    this.event = event.getClass().getSimpleName();
    this.occuredAt = event.getOccuredAt();
    try {
      this.data =  mapper.writeValueAsString(event.getSource());
    } catch (Exception e) {
    }
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Instant occuredAt;
  private String event;
  private String data;
}
