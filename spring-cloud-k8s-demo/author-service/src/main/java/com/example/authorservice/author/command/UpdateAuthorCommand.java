package com.example.authorservice.author.command;

import javax.validation.constraints.NotNull;
import lombok.Data;

/**
* UpdateAuthorCommand
*/
@Data
public class UpdateAuthorCommand {
	private Long id;
	@NotNull
	private String name;
}
