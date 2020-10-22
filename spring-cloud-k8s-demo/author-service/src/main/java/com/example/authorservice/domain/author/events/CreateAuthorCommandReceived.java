package com.example.authorservice.domain.author.events;

import com.example.authorservice.domain.author.command.CreateAuthorCommand;

/**
 * CreateAuthorCommandReceived
 */
public class CreateAuthorCommandReceived extends CommandEvent<CreateAuthorCommand> {

  private static final long serialVersionUID = 1L;

  public CreateAuthorCommandReceived(CreateAuthorCommand source) {
    super(source);
  }
}
