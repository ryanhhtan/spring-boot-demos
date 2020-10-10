package com.example.bookservice.book.service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import com.example.bookservice.book.command.CreateBookCommand;
import com.example.bookservice.book.command.DeleteBookCommand;
import com.example.bookservice.book.command.UpdateBookCommand;
import com.example.bookservice.book.exception.BookNotFoundException;
import com.example.bookservice.book.exdata.model.Author;
import com.example.bookservice.book.exdata.resolver.AuthorResolver;
import com.example.bookservice.book.model.Book;
import com.example.bookservice.book.model.BookFactory;
import com.example.bookservice.book.query.BookView;
import com.example.bookservice.book.repository.BookRepository;
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
public class DefaultBookService implements BookService {

	private final BookRepository repository;

	private final AuthorResolver authorResolver;

	@Override
	public BookView createBook(CreateBookCommand command) throws Exception {
		log.info("*** create book command: {}", command);
		final Book book = BookFactory.createBook(command.getTitle());
		final Set<Long> authorIds = command.getAuthorRefs();
		if (Objects.nonNull(authorIds) && !authorIds.isEmpty()) {
			final Set<Author> authors =
					authorResolver.findById(command.getAuthorRefs()).stream().collect(Collectors.toSet());
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
		return repository.findById(id).map(book -> new BookView(book, authorResolver))
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
