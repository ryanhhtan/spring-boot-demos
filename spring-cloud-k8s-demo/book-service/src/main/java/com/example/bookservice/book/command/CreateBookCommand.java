package com.example.bookservice.book.command;

import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
* CreateBookCommand
*/
@Data
public class CreateBookCommand {
	@NotNull
	private String title;
	private Set<Long> authorRefs;
}
