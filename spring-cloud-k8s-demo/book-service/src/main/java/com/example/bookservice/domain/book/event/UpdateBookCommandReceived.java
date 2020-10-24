package com.example.bookservice.domain.book.event;

import com.example.bookservice.common.event.CommandEvent;
import com.example.bookservice.domain.book.command.UpdateBookCommand;

/**
 * UpdateBookCommandReceived
 */
public class UpdateBookCommandReceived extends CommandEvent<UpdateBookCommand> {

  private static final long serialVersionUID = 1L;

  public UpdateBookCommandReceived(UpdateBookCommand source) {
    super(source);
  }
}
