package com.example.bookservice.common.event;

/**
 * DomainEvent
 */
public class DomainEvent<T extends Domain> extends BaseEvent {

  private static final long serialVersionUID = 1L;
  public DomainEvent(T source) {
    super(source);
  }
}
