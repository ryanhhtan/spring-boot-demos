package com.example.bookservice.domain.book.query;

import java.util.Objects;
import java.util.Set;
import com.example.bookservice.domain.author.model.Author;
import com.example.bookservice.domain.author.resolver.AuthorResolver;
import com.example.bookservice.domain.book.model.Book;

/**
 * BookViewFactory
 */
public class BookViewFactory {
	private BookViewFactory() {
	}

	private static BookViewFactory factory;

	public static BookViewFactory instance() {
		if (Objects.isNull(factory)) {
			factory = new BookViewFactory();
		}
		return factory;
	}

	public BookView createBookView(final Book book, final Set<Author> authors) {
		return new BookView(book, authors, null);
	}

	public BookView createBookView(final Book book, final AuthorResolver authorResolver) {
		return new BookView(book, null, authorResolver);
	}
}
