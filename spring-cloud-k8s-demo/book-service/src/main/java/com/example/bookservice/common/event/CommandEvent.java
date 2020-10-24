package com.example.bookservice.common.event;

/**
 * CommandEvent
 */
public class CommandEvent<T extends Command> extends BaseEvent {

  private static final long serialVersionUID = 1L;

  public CommandEvent(T source) {
    super(source);
  }
}
