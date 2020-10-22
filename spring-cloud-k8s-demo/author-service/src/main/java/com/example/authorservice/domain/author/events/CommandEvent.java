package com.example.authorservice.domain.author.events;


/**
 * CommandEvent
 */
public abstract class CommandEvent<T> extends BaseEvent<T>{

  private static final long serialVersionUID = 1L;

  public CommandEvent(T source) {
    super(source);
  }
}
