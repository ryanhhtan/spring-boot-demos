package com.example.bookservice.book.command;

import lombok.Data;
import lombok.experimental.Accessors;

/**
* DeleteBookCommand
*/
@Data
@Accessors(chain = true)
public class DeleteBookCommand {
	private Long id;
}
