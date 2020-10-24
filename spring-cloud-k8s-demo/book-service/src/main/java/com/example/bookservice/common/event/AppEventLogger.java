package com.example.bookservice.common.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

/**
 * AppEventLogger
 */
@Component
@RequiredArgsConstructor
public class AppEventLogger {
  private final EventRecordRepository repository;

  @EventListener
  public void logAppEvents(BaseEvent event) {
    final EventRecord record = new EventRecord(event);
    repository.save(record);
  }
}
