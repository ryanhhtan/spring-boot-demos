package com.example.bookservice.book.model;

/**
* BookFactor
*/
public class BookFactory {

	public static Book createBook(final String title) {
		return new Book().setTitle(title);
	}
}
