package com.example.bookservice.domain.book.command;

import com.example.bookservice.common.event.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* DeleteBookCommand
*/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class DeleteBookCommand extends Command {
	private Long id;
}
