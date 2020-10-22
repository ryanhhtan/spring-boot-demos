package com.example.authorservice.eventhandler;

import com.example.authorservice.common.Aggregate;

/**
 * DomainEvent
 */
public abstract class DomainEvent<T extends Aggregate<?>>  extends BaseEvent<T> {

  private static final long serialVersionUID = 1L;

  public DomainEvent(T source) {
    super(source);
  }
}
