package com.example.bookservice.domain.book.command;

import java.util.Set;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
* UpdateBookCommand
*/
@Data
public class UpdateBookCommand {
	private Long id;

	@NotBlank
	private String title;

	private Set<Long> authorIds;
}
