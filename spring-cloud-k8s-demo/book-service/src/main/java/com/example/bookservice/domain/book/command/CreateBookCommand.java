package com.example.bookservice.domain.book.command;

import java.util.Set;
import javax.validation.constraints.NotNull;
import com.example.bookservice.domain.author.model.Author;
import lombok.Data;

/**
* CreateBookCommand
*/
@Data
public class CreateBookCommand {
	@NotNull
	private String title;
	private Set<Author> authors;
}
