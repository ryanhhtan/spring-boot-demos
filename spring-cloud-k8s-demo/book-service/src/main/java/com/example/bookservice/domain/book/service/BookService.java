package com.example.bookservice.domain.book.service;

import java.util.List;
import java.util.Map;
import com.example.bookservice.domain.book.command.CreateBookCommand;
import com.example.bookservice.domain.book.command.DeleteBookCommand;
import com.example.bookservice.domain.book.command.UpdateBookCommand;
import com.example.bookservice.domain.book.query.BookView;

/**
* BookeService
*/
public interface BookService {
	public BookView createBook(CreateBookCommand command) throws Exception;
	public List<BookView> findAll(final Map<String, String> queryParams);
	public BookView findById(final Long id) throws Exception;
	public BookView updateBook(UpdateBookCommand command) throws Exception;
	public void deleteBook(DeleteBookCommand command);
  public void removeInvalidAuthorFromBooks(final Long authorId);
}
