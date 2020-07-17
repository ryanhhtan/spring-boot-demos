package com.example.webfluxdemo.repository;

import java.util.ArrayList;
import java.util.List;
import com.example.webfluxdemo.entity.Todo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * TodoRepository
 */
@Repository
public class TodoRepository {
  private List<Todo> todoList = new ArrayList<>();

  public Mono<Todo> create(final Mono<Todo> todoMono) {
    return todoMono.map(todo -> {
      todoList.add(todo.setId(generateId()));
      return todo;
    });
  }

  public Flux<Todo> all() {
    return Flux.fromIterable(todoList);
  }

  public Mono<Todo> findById(final Long id ) {
    return Mono.justOrEmpty(todoList.stream().filter(todo -> todo.getId().equals(id)).findFirst());
  }

  public Mono<Todo> update(final Long id, final Mono<Todo> updateTodoMono) {
    return Mono.zip(findById(id), updateTodoMono).map(z ->{
      final Todo target = z.getT1();
      final Todo source = z.getT2(); 
      source.setId(target.getId());
      BeanUtils.copyProperties(source, target);
      return target;
    });
  }

  public Mono<Todo> delete(final Long id) {
    return findById(id).map(todo -> {
      todoList.remove(todo);
      return todo;
    });
  }

  private Long generateId() {
    synchronized(todoList) {
      if (todoList.isEmpty()) {
        return 1L;
      }
      return todoList.get(todoList.size() -1).getId() + 1; 
    }
  }
}
