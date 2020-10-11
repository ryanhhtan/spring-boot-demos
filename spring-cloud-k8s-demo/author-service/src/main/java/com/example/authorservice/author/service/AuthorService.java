package com.example.authorservice.author.service;

import java.util.List;
import java.util.Map;
import com.example.authorservice.author.command.CreateAuthorCommand;
import com.example.authorservice.author.command.DeleteAuthorCommand;
import com.example.authorservice.author.command.UpdateAuthorCommand;
import com.example.authorservice.author.query.AuthorView ;
import com.example.authorservice.author.exception.AuthorNotFoundException;

/**
* AuthorService
*/
public interface AuthorService {
	public AuthorView  createAuthor(final CreateAuthorCommand command);
	public List<AuthorView > findAll(final Map<String, String> queryParams) throws Exception;
	public AuthorView  findById(final Long id) throws AuthorNotFoundException;
	public AuthorView  updateAuthor(UpdateAuthorCommand command) throws AuthorNotFoundException;
	public void deleteById(final DeleteAuthorCommand command);
}
