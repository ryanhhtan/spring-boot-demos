package com.example.authorservice.author.service;

import java.util.List;
import com.example.authorservice.author.controller.command.CreateAuthorCommand;
import com.example.authorservice.author.controller.command.DeleteAuthorCommand;
import com.example.authorservice.author.controller.command.UpdateAuthorCommand;
import com.example.authorservice.author.controller.query.AuthorBasicView;
import com.example.authorservice.author.exception.AuthorNotFoundException;

/**
* AuthorService
*/
public interface AuthorService {
	public AuthorBasicView createAuthor(final CreateAuthorCommand command);
	public List<AuthorBasicView> findAll();
	public AuthorBasicView findById(final Long id) throws AuthorNotFoundException;
	public AuthorBasicView updateAuthor(UpdateAuthorCommand command) throws AuthorNotFoundException;
	public void deleteById(final DeleteAuthorCommand command);
}
