package com.example.authorservice.author.service;

import java.util.List;
import java.util.stream.Collectors;
import com.example.authorservice.author.controller.command.CreateAuthorCommand;
import com.example.authorservice.author.controller.command.DeleteAuthorCommand;
import com.example.authorservice.author.controller.command.UpdateAuthorCommand;
import com.example.authorservice.author.controller.query.AuthorBasicView;
import com.example.authorservice.author.exception.AuthorNotFoundException;
import com.example.authorservice.author.model.Author;
import com.example.authorservice.author.model.AuthorFactory;
import com.example.authorservice.author.repository.AuthorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AuthorServiceImpl
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {
	private final AuthorRepository repository;

	@Override
	public AuthorBasicView createAuthor(CreateAuthorCommand command) {
		final Author author = AuthorFactory.createAuthor(command.getName());
		final Author saved = repository.save(author);
		return new AuthorBasicView(saved);
	}

	@Override
	public List<AuthorBasicView> findAll() {
		return repository.findAll().stream().map(AuthorBasicView::new).collect(Collectors.toList());
	}

	@Override
	public AuthorBasicView findById(Long id) throws AuthorNotFoundException {
		return repository.findById(id).map(AuthorBasicView::new).orElseThrow(this::authorNotFound);
	}

	@Override
	public AuthorBasicView updateAuthor(UpdateAuthorCommand command) throws AuthorNotFoundException {
		log.info("*** command: {}", command);
		final Author author = repository.findById(command.getId()).orElseThrow(this::authorNotFound);
		BeanUtils.copyProperties(command, author, "id");
		final Author updated = repository.save(author);
		return new AuthorBasicView(updated);
	}

	private AuthorNotFoundException authorNotFound() {
		return new AuthorNotFoundException("author not found");
	}

	@Override
	public void deleteById(DeleteAuthorCommand command) {
		repository.deleteById(command.getId());
	}
}
