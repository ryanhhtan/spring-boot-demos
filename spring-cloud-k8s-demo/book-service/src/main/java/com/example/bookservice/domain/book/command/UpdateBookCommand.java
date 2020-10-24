package com.example.bookservice.domain.book.command;

import java.util.Set;
import com.example.bookservice.common.event.Command;
import com.example.bookservice.domain.author.model.Author;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* UpdateBookCommand
*/
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateBookCommand extends Command {
	private Long id;

	private String title;

	private Set<Author> authors;
}
