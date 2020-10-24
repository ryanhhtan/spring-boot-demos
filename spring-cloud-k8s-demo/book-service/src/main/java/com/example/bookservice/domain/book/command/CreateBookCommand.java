package com.example.bookservice.domain.book.command;

import java.util.Set;
import javax.validation.constraints.NotNull;
import com.example.bookservice.common.event.Command;
import com.example.bookservice.domain.author.model.Author;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* CreateBookCommand
*/
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateBookCommand extends Command {
	@NotNull
	private String title;
	private Set<Author> authors;
}
