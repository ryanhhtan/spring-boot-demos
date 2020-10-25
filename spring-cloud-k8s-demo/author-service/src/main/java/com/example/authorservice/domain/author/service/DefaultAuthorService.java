package com.example.authorservice.domain.author.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.example.authorservice.domain.author.command.CreateAuthorCommand;
import com.example.authorservice.domain.author.command.DeleteAuthorCommand;
import com.example.authorservice.domain.author.command.UpdateAuthorCommand;
import com.example.authorservice.domain.author.events.AuthorCreated;
import com.example.authorservice.domain.author.events.AuthorDeleted;
import com.example.authorservice.domain.author.events.AuthorUpdated;
import com.example.authorservice.domain.author.events.CreateAuthorCommandReceived;
import com.example.authorservice.domain.author.events.DeleteAuthorCommandReceived;
import com.example.authorservice.domain.author.events.UpdateAuthorCommandReceived;
import com.example.authorservice.domain.author.query.AuthorView ;
import com.example.authorservice.domain.author.exception.AuthorNotFoundException;
import com.example.authorservice.domain.author.model.Author;
import com.example.authorservice.domain.author.model.AuthorFactory;
import com.example.authorservice.domain.author.repository.AuthorRepository;
import com.example.authorservice.domain.author.spec.AuthorSpec;
import com.example.authorservice.eventhandler.OutgoingEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

/**
 * AuthorServiceImpl
 */
@Service
@RequiredArgsConstructor
public class DefaultAuthorService implements AuthorService {
	private final AuthorRepository repository;
	private final StreamBridge streamBridge;
	private final ApplicationEventPublisher publisher;
	private String authorEventBinding = "sendAuthorEvent-out-0"; 

	@Override
	public AuthorView  createAuthor(CreateAuthorCommand command) {
		final CreateAuthorCommandReceived commandReceived = new CreateAuthorCommandReceived(command);
		publisher.publishEvent(commandReceived);
		final Author author = AuthorFactory.createAuthor(command.getName());
		final Author saved = repository.save(author);
		final AuthorCreated authorCreated = new AuthorCreated(saved);
		final OutgoingEvent event = new OutgoingEvent(authorCreated);
		streamBridge.send(authorEventBinding, event);
		publisher.publishEvent(authorCreated);
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
		final UpdateAuthorCommandReceived commandReceived = new UpdateAuthorCommandReceived(command);
		publisher.publishEvent(commandReceived);
		final Author author = repository.findById(command.getId()).orElseThrow(this::authorNotFound);
		BeanUtils.copyProperties(command, author, "id");
		final Author updated = repository.save(author);

		final AuthorUpdated authorUpdated = new AuthorUpdated (updated);
		final OutgoingEvent event = new OutgoingEvent(authorUpdated);
		streamBridge.send(authorEventBinding, event);
		publisher.publishEvent(authorUpdated);
		return new AuthorView (updated);
	}

	private AuthorNotFoundException authorNotFound() {
		return new AuthorNotFoundException("author not found");
	}

	@Override
	public void deleteById(DeleteAuthorCommand command) {
		final DeleteAuthorCommandReceived commandReceived = new DeleteAuthorCommandReceived(command);
		publisher.publishEvent(commandReceived);
		final Author author = repository.findById(command.getId()).orElseThrow(this::authorNotFound);
		repository.deleteById(command.getId());
		final AuthorDeleted authorDeleted = new AuthorDeleted(author);
		final OutgoingEvent event = new OutgoingEvent(authorDeleted);
		streamBridge.send(authorEventBinding, event);
		publisher.publishEvent(authorDeleted);
	}
}
