package com.example.authorservice.domain.author.events;

import com.example.authorservice.domain.author.command.DeleteAuthorCommand;

/**
 * DeleteAuthorCommandReceived
 */
public class DeleteAuthorCommandReceived extends CommandEvent<DeleteAuthorCommand> {

  private static final long serialVersionUID = 1L;

  public DeleteAuthorCommandReceived(DeleteAuthorCommand source) {
    super(source);
  }
}
