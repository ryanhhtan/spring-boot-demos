package com.example.authorservice.domain.author.events;

import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

/**
 * DomainRecord
 */
@Entity
@Getter
public class DomainEventRecord {
  private static final ObjectMapper mapper = new ObjectMapper();

  DomainEventRecord(){}
  public DomainEventRecord(BaseEvent<?> event) {
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
