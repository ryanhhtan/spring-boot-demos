package com.example.authorservice.author.query;

import com.example.authorservice.author.model.Author;

/**
* AuthorBasicView
*/
public class AuthorView {

	private Long id;
	private String name;


	public AuthorView(final Author author) {
		this.id = author.getId();
		this.name = author.getName();
	}

	public String getName() {
		return this.name;
	}

	public Long getId() {
		return this.id;
	}
}
