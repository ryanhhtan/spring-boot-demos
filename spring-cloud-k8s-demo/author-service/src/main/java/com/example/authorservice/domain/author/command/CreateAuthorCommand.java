package com.example.authorservice.domain.author.command;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* CreateAuthorCommand
*/
@Data
@Accessors(chain = true)
public class CreateAuthorCommand {
	@NotNull
	private String name;
}
