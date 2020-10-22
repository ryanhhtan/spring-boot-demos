package com.example.authorservice.domain.author.events;

import javax.persistence.EntityManager;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

/**
 * EventHandler
 */
@Component
@RequiredArgsConstructor
public class EventHandler {

  private final EntityManager em;
  // private StreamBridge streamBridge;

  @EventListener
  public void handleCommandEvent(final CommandEvent<?> event) {
    em.persist(new DomainEventRecord(event));
  }

  @EventListener
  public void handleDomainEvent(final DomainEvent<?> event) {
    em.persist(new DomainEventRecord(event));
  }

  
}
