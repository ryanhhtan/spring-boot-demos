package com.example.authorservice.author.model;

/**
* AuthorFactory
*/
public class AuthorFactory {
	public static Author createAuthor(final String name) {
		return new Author(name);
	}
}
