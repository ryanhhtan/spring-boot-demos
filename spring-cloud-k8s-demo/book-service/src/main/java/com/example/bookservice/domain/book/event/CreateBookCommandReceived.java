package com.example.bookservice.domain.book.event;

import com.example.bookservice.common.event.CommandEvent;
import com.example.bookservice.domain.book.command.CreateBookCommand;

/**
 * CreateBookCommandReceived
 */
public class CreateBookCommandReceived extends CommandEvent<CreateBookCommand> {

  private static final long serialVersionUID = 1L;

  public CreateBookCommandReceived(CreateBookCommand source) {
    super(source);
  }
}
