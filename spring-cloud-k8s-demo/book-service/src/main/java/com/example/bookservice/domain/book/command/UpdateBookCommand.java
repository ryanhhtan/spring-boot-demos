package com.example.bookservice.domain.book.command;

import java.util.Set;
import com.example.bookservice.domain.author.model.Author;
import lombok.Data;

/**
* UpdateBookCommand
*/
@Data
public class UpdateBookCommand {
	private Long id;

	private String title;

	private Set<Author> authors;
}
