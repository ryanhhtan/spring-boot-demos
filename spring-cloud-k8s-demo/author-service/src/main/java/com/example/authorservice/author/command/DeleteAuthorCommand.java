package com.example.authorservice.author.command;

import com.example.authorservice.author.model.Author;
import lombok.Data;

/**
* DeleteAuthorCommand
*/
@Data
public class DeleteAuthorCommand {
	private Long id;
	private Author author;
  public DeleteAuthorCommand(Long id) {
    this.id = id;
  }
}
