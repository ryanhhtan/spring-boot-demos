package com.example.bookservice.domain.book.controller;

import java.util.List;
import com.example.bookservice.domain.book.command.CreateBookCommand;
import com.example.bookservice.domain.book.command.DeleteBookCommand;
import com.example.bookservice.domain.book.command.UpdateBookCommand;
import com.example.bookservice.domain.book.query.BookView;
import com.example.bookservice.domain.book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

/**
 * BookController
 */
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
	private final BookService service;


	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BookView createBook(@RequestBody @Validated CreateBookCommand command) throws Exception {
		return service.createBook(command);
	}

	@GetMapping
	public List<BookView> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public BookView findById(@PathVariable(name = "id") final Long id) throws Exception {
		return service.findById(id);
	}

	@PutMapping("/{id}")
	public BookView updateBook(@PathVariable(name = "id") final Long id,
			@RequestBody @Validated final UpdateBookCommand command) throws Exception {
		command.setId(id);
		return service.updateBook(command);
	}

	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable(name = "id") final Long id) {
		service.deleteBook(new DeleteBookCommand().setId(id));
	}

}
