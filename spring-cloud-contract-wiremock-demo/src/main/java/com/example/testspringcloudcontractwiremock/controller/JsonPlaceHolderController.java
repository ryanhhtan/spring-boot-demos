package com.example.testspringcloudcontractwiremock.controller;

import java.util.List;
import com.example.testspringcloudcontractwiremock.pojo.Todo;
import com.example.testspringcloudcontractwiremock.service.JsonPlaceHolderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * JsonPlaceHolderController
 */
@RestController
@RequiredArgsConstructor
public class JsonPlaceHolderController {

  @NonNull
  private JsonPlaceHolderService jphs;

  @GetMapping("/todos/{id}")
  public Todo findTodoById(@PathVariable(name = "id") Long id) {
    return jphs.getTodoById(id);
  }

  @GetMapping("/todos")
  public List<Todo> getAllTodos() {
    return jphs.getAllTodos();
  }

  @PostMapping("/todos")
  public Todo createTodo(@RequestBody final Todo todo) {
    return jphs.createTodo(todo);
  }
}
