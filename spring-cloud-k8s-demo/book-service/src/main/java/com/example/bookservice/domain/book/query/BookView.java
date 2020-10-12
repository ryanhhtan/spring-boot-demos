package com.example.bookservice.domain.book.query;


import java.util.Set;
import java.util.stream.Collectors;
import com.example.bookservice.domain.author.model.Author;
import com.example.bookservice.domain.author.resolver.AuthorResolver;
import com.example.bookservice.domain.book.model.AuthorRef;
import com.example.bookservice.domain.book.model.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;
import lombok.Data;

/**
 * BookBasicView
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookView {

	@JsonIgnore
	private AuthorResolver authorResolver;

	private Long id;

	private String title;

	@JsonIgnore
	private Set<AuthorRef> authorRefs;

	private Set<Author> authors;

	public BookView(final Book book, final AuthorResolver authorResolver) {
		BeanUtils.copyProperties(book, this);
		this.authorResolver = authorResolver;
		this.setAuthors(authorRefs.stream().map(it -> new Author().setId(it.getAuthorId()))
				.collect(Collectors.toSet()));
	}

	public BookView withAuthors() {
		this.setAuthors(authorResolver
				.findById(authorRefs.stream().map(AuthorRef::getAuthorId).collect(Collectors.toSet()))
				.stream().collect(Collectors.toSet()));
		return this;
	}
}
