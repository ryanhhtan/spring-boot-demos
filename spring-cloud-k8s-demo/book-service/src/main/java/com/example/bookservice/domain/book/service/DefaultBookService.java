package com.example.bookservice.domain.book.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.example.bookservice.domain.book.command.CreateBookCommand;
import com.example.bookservice.domain.book.command.DeleteBookCommand;
import com.example.bookservice.domain.book.command.UpdateBookCommand;
import com.example.bookservice.common.WithId;
import com.example.bookservice.domain.book.exception.AuthorNotExistException;
import com.example.bookservice.domain.book.exception.BookNotFoundException;
import com.example.bookservice.domain.author.model.Author;
import com.example.bookservice.domain.author.resolver.AuthorResolver;
import com.example.bookservice.domain.book.model.Book;
import com.example.bookservice.domain.book.model.BookFactory;
import com.example.bookservice.domain.book.query.BookView;
import com.example.bookservice.domain.book.query.BookViewFactory;
import com.example.bookservice.domain.book.repository.BookRepository;
import com.example.bookservice.domain.book.spec.BookSpec;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DefaultBookService
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DefaultBookService implements BookService {

	private final BookRepository repository;

	private final AuthorResolver authorResolver;

	@Override
	public BookView createBook(CreateBookCommand command) throws Exception {
		log.info("*** create book command: {}", command);
		final Book book = BookFactory.createBook(command.getTitle());
		final Set<Author> maybeAuthors = command.getAuthors();
		final Set<Author> actualAuthors = new HashSet<>();
		if (Objects.nonNull(maybeAuthors) && !maybeAuthors.isEmpty()) {
			actualAuthors.addAll(authorResolver.findById(WithId.extractIds(maybeAuthors)));
			if (maybeAuthors.size() != actualAuthors.size()) {
				maybeAuthors.removeAll(actualAuthors);
				final String notExistAuthorIds = WithId.extractIdsString(maybeAuthors);
				throw new AuthorNotExistException("author id(s) not exist: " + notExistAuthorIds);
			}
			book.addAuthors(actualAuthors);
		}
		final Book saved = repository.save(book);
		return BookViewFactory.instance().createBookView(saved, actualAuthors);
	}

	@Override
	public List<BookView> findAll(final Map<String, String> queryParams) {
		final Specification<Book> specs = BookSpec.query(queryParams);
		return repository.findAll(specs).stream()
				.map(book -> BookViewFactory.instance().createBookView(book, authorResolver).withAuthors())
				.collect(Collectors.toList());
	}

	@Override
	public BookView findById(Long id) throws Exception {
		return repository.findById(id)
				.map(book -> BookViewFactory.instance().createBookView(book, authorResolver).withAuthors())
				.orElseThrow(this::notFound);
	}

	@Override
	public BookView updateBook(UpdateBookCommand command) throws Exception {
		final Book book = repository.findById(command.getId()).orElseThrow(this::notFound);
		BeanUtils.copyProperties(command, book, "id", "authors");
		final Set<Author> maybeAuthors = command.getAuthors();
		final Set<Author> actualAuthors = new HashSet<>();
		if (Objects.nonNull(maybeAuthors)) {
			book.deleteAllAuthors();
			if (!maybeAuthors.isEmpty()) {
				actualAuthors.addAll(authorResolver.findById(WithId.extractIds(maybeAuthors)));
				if (actualAuthors.size() != maybeAuthors.size()) {
					maybeAuthors.removeAll(actualAuthors);
					throw new AuthorNotExistException(
							"author id(s) not exist: " + WithId.extractIdsString(maybeAuthors));
				}
				book.addAuthors(actualAuthors);
			}
		}
		final Book updated = repository.save(book);
		return BookViewFactory.instance().createBookView(updated, actualAuthors);
	}

	@Override
	public void deleteBook(DeleteBookCommand command) {
		repository.deleteById(command.getId());
	}

	public BookNotFoundException notFound() {
		return new BookNotFoundException();
	}
}

