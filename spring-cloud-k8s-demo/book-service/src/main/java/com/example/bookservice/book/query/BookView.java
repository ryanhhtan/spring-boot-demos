package com.example.bookservice.book.query;

import java.util.Set;
import java.util.stream.Collectors;
import com.example.bookservice.book.exdata.model.Author;
import com.example.bookservice.book.exdata.resolver.AuthorResolver;
import com.example.bookservice.book.model.AuthorRef;
import com.example.bookservice.book.model.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;
import lombok.Data;

/**
 * BookBasicView
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BookView {

	@JsonIgnore
	private AuthorResolver authorResolver;

	private Long id;

	private String title;

	private Set<AuthorRef> authorRefs;

	private Set<Author> authors;

	public BookView(final Book book, final AuthorResolver authorResolver) {
		BeanUtils.copyProperties(book, this);
		this.authorResolver = authorResolver;
	}

	public BookView withAuthors() {
		this.setAuthors(authorResolver
				.findById(authorRefs.stream().map(AuthorRef::getAuthorId).collect(Collectors.toSet()))
				.stream().collect(Collectors.toSet()));
		return this;
	}
}
