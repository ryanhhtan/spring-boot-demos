package com.example.testspringcloudcontractwiremock.service;

import java.util.List;
import com.example.testspringcloudcontractwiremock.pojo.Todo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * JsonPlaceHolderService
 */
@Service
public class JsonPlaceHolderService {
  @Value("${jsonplaceholder.url}")
  private String url;

  private RestTemplate rest = new RestTemplate();
  
  public JsonPlaceHolderService setUrl(final String url) {
    this.url = url;
    return this;
  }

  public Todo getTodoById(final Long id) {
    return rest.getForObject(String.format("%s/todos/%d", url, id), Todo.class);
  }

  @SuppressWarnings(value = "unchecked")
  public List<Todo> getAllTodos() {
    return rest.getForObject(String.format("%s/todos", url), List.class);
  }


  public Todo createTodo(final Todo todo) {
    return rest.postForObject(String.format("%s/todos", url), todo, Todo.class);
  }
}
