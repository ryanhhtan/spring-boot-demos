package com.example.authorservice.author.controller.query;

import com.example.authorservice.author.model.Author;

/**
* AuthorBasicView
*/
public class AuthorBasicView {

	private Author author;

	public AuthorBasicView(final Author author) {
		this.author = author;
	}

	public String getName() {
		return author.getName();
	}

	public Long getId() {
		return author.getId();
	}
}
