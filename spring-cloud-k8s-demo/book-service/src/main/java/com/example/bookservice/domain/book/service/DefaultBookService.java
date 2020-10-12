package com.example.bookservice.domain.book.service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.example.bookservice.domain.book.command.CreateBookCommand;
import com.example.bookservice.domain.book.command.DeleteBookCommand;
import com.example.bookservice.domain.book.command.UpdateBookCommand;
import com.example.bookservice.domain.book.exception.AuthorNotExistException;
import com.example.bookservice.domain.book.exception.BookNotFoundException;
import com.example.bookservice.domain.author.model.Author;
import com.example.bookservice.domain.author.resolver.AuthorResolver;
import com.example.bookservice.domain.book.model.Book;
import com.example.bookservice.domain.book.model.BookFactory;
import com.example.bookservice.domain.book.query.BookView;
import com.example.bookservice.domain.book.repository.BookRepository;
import org.springframework.beans.BeanUtils;
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
		final Set<Long> authorIds =
				command.getAuthors().stream().map(Author::getId).collect(Collectors.toSet());
		if (Objects.nonNull(authorIds) && !authorIds.isEmpty()) {
			final Set<Author> authors =
					authorResolver.findById(authorIds).stream().collect(Collectors.toSet());
			if (authors.size() != authorIds.size()) {
				authorIds.removeAll(authors.stream().map(Author::getId).collect(Collectors.toSet()));
				final String notExistAuthorIds = authorIds.stream().map(it -> it.toString()).reduce(null,
						(acc, cur) -> Objects.isNull(acc) ? cur : acc.concat(", ").concat(cur));
				throw new AuthorNotExistException("author id(s) not exist: " + notExistAuthorIds);
			}
			book.addAuthors(authors);
		}
		final Book saved = repository.save(book);
		return new BookView(saved, authorResolver);
	}

	@Override
	public List<BookView> findAll() {
		return repository.findAll().stream().map(book -> new BookView(book, authorResolver))
				.collect(Collectors.toList());
	}

	@Override
	public BookView findById(Long id) throws Exception {
		return repository.findById(id).map(book -> new BookView(book, authorResolver).withAuthors())
				.orElseThrow(this::notFound);
	}

	@Override
	public BookView updateBook(UpdateBookCommand command) throws Exception {
		final Book book = repository.findById(command.getId()).orElseThrow(this::notFound);
		BeanUtils.copyProperties(command, book, "id");
		return null;
	}

	@Override
	public void deleteBook(DeleteBookCommand command) {
		repository.deleteById(command.getId());
	}

	public BookNotFoundException notFound() {
		return new BookNotFoundException();
	}
}
