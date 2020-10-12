package com.example.bookservice.domain.book.model;

/**
* BookFactor
*/
public class BookFactory {

	public static Book createBook(final String title) {
		return new Book().setTitle(title);
	}
}
