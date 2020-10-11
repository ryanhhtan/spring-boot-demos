package com.example.authorservice.author.controller;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import com.example.authorservice.author.command.CreateAuthorCommand;
import com.example.authorservice.author.command.DeleteAuthorCommand;
import com.example.authorservice.author.command.UpdateAuthorCommand;
import com.example.authorservice.author.query.AuthorView ;
import com.example.authorservice.author.exception.AuthorNotFoundException;
import com.example.authorservice.author.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

/**
 * AuthorController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

	private final AuthorService service;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public AuthorView  createAuthor(@Validated @RequestBody final CreateAuthorCommand command) {
		return service.createAuthor(command);
	}

	@GetMapping
	public List<AuthorView> findAll(@RequestParam Map<String, String> queryParams) {
		return service.findAll(queryParams);
	}

	@GetMapping("/{id}")
	public AuthorView  findById(@PathVariable(name = "id") final Long id)
			throws AuthorNotFoundException {
		return service.findById(id);
	}

	@PutMapping("/{id}")
	public AuthorView  updateById(@PathVariable(name = "id") final Long id,
			@RequestBody @Valid final UpdateAuthorCommand command) throws AuthorNotFoundException {
    command.setId(id);
		return service.updateAuthor(command);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(name = "id") final Long id) {
		service.deleteById(new DeleteAuthorCommand(id));
	}
}
