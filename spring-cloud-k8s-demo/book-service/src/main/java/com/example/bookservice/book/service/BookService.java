package com.example.bookservice.book.service;

import java.util.List;
import com.example.bookservice.book.command.CreateBookCommand;
import com.example.bookservice.book.command.DeleteBookCommand;
import com.example.bookservice.book.command.UpdateBookCommand;
import com.example.bookservice.book.query.BookView;

/**
* BookeService
*/
public interface BookService {
	public BookView createBook(CreateBookCommand command) throws Exception;
	public List<BookView> findAll();
	public BookView findById(final Long id) throws Exception;
	public BookView updateBook(UpdateBookCommand command) throws Exception;
	public void deleteBook(DeleteBookCommand command);
}
