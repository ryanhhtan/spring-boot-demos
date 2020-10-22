package com.example.authorservice.domain.author.events;

/**
 * DomainEvent
 */
public abstract class DomainEvent<T>  extends BaseEvent<T> {

  private static final long serialVersionUID = 1L;

  public DomainEvent(T source) {
    super(source);
  }
}
