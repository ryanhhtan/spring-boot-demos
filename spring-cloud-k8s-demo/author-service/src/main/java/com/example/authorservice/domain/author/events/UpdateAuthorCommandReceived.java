package com.example.authorservice.domain.author.events;

import com.example.authorservice.domain.author.command.UpdateAuthorCommand;

/**
 * UpdateAuthorCommandReceived
 */
public class UpdateAuthorCommandReceived extends CommandEvent<UpdateAuthorCommand> {

  private static final long serialVersionUID = 1L;

  public UpdateAuthorCommandReceived(UpdateAuthorCommand source) {
    super(source);
  }
}
