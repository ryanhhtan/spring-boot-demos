package com.example.bookservice.domain.book.controller;

import java.util.List;
import java.util.Map;
import com.example.bookservice.domain.book.command.CreateBookCommand;
import com.example.bookservice.domain.book.command.DeleteBookCommand;
import com.example.bookservice.domain.book.command.UpdateBookCommand;
import com.example.bookservice.domain.book.event.CreateBookCommandReceived;
import com.example.bookservice.domain.book.event.DeleteBookCommandReceived;
import com.example.bookservice.domain.book.event.UpdateBookCommandReceived;
import com.example.bookservice.domain.book.query.BookView;
import com.example.bookservice.domain.book.service.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

/**
 * BookController
 */
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
  private final BookService service;

  private final ApplicationEventPublisher publisher;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BookView createBook(@RequestBody @Validated CreateBookCommand command) throws Exception {
    publisher.publishEvent(new CreateBookCommandReceived(command));
    return service.createBook(command);
  }

  @GetMapping
  public List<BookView> findAll(@RequestParam(required = false) Map<String, String> queryParams) {
    return service.findAll(queryParams);
  }

  @GetMapping("/{id}")
  public BookView findById(@PathVariable(name = "id") final Long id) throws Exception {
    return service.findById(id);
  }

  @PutMapping("/{id}")
  public BookView updateBook(@PathVariable(name = "id") final Long id,
      @RequestBody @Validated final UpdateBookCommand command) throws Exception {
    command.setId(id);
    publisher.publishEvent(new UpdateBookCommandReceived(command));
    return service.updateBook(command);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void deleteBook(@PathVariable(name = "id") final Long id) {
    final DeleteBookCommand command = new DeleteBookCommand().setId(id);
    publisher.publishEvent(new DeleteBookCommandReceived(command));
    service.deleteBook(command);
  }
}
