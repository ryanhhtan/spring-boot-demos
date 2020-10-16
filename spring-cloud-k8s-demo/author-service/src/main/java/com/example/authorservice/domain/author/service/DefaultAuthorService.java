package com.example.authorservice.domain.author.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.example.authorservice.domain.author.command.CreateAuthorCommand;
import com.example.authorservice.domain.author.command.DeleteAuthorCommand;
import com.example.authorservice.domain.author.command.UpdateAuthorCommand;
import com.example.authorservice.domain.author.query.AuthorView ;
import com.example.authorservice.domain.author.exception.AuthorNotFoundException;
import com.example.authorservice.domain.author.model.Author;
import com.example.authorservice.domain.author.model.AuthorFactory;
import com.example.authorservice.domain.author.repository.AuthorRepository;
import com.example.authorservice.domain.author.spec.AuthorSpec;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AuthorServiceImpl
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultAuthorService implements AuthorService {
	private final AuthorRepository repository;
	private final StreamBridge streamBridge;

	@Override
	public AuthorView  createAuthor(CreateAuthorCommand command) {
		final Author author = AuthorFactory.createAuthor(command.getName());
		final Author saved = repository.save(author);
		return new AuthorView (saved);
	}

	@Override
	public List<AuthorView > findAll(final Map<String, String> queryParams) throws Exception{
		return repository.findAll(AuthorSpec.query(queryParams)).stream().map(AuthorView ::new)
				.collect(Collectors.toList());
	}

	@Override
	public AuthorView  findById(Long id) throws AuthorNotFoundException {
		return repository.findById(id).map(AuthorView ::new).orElseThrow(this::authorNotFound);
	}

	@Override
	public AuthorView  updateAuthor(UpdateAuthorCommand command) throws AuthorNotFoundException {
		log.info("*** command: {}", command);
		final Author author = repository.findById(command.getId()).orElseThrow(this::authorNotFound);
		BeanUtils.copyProperties(command, author, "id");
		final Author updated = repository.save(author);
		return new AuthorView (updated);
	}

	private AuthorNotFoundException authorNotFound() {
		return new AuthorNotFoundException("author not found");
	}

	@Override
	public void deleteById(DeleteAuthorCommand command) {
		repository.deleteById(command.getId());
	}
}
