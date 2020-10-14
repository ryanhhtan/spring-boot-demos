package com.example.authorservice.domain.author.model;

/**
* AuthorFactory
*/
public class AuthorFactory {
	public static Author createAuthor(final String name) {
		return new Author(name);
	}
}
