package com.example.bookservice.domain.book.event;

import com.example.bookservice.common.event.CommandEvent;
import com.example.bookservice.domain.book.command.DeleteBookCommand;

/**
 * DeleteBookCommandReceived
 */
public class DeleteBookCommandReceived extends CommandEvent<DeleteBookCommand> {

  private static final long serialVersionUID = 1L;

  public DeleteBookCommandReceived(DeleteBookCommand source) {
    super(source);
  }
}
