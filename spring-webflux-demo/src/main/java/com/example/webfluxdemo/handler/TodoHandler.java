package com.example.webfluxdemo.handler;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import com.example.webfluxdemo.entity.Todo;
import com.example.webfluxdemo.repository.TodoRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.server.ServerResponse.BodyBuilder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * UserHandler
 */
@Service
@RequiredArgsConstructor
public class TodoHandler {
  @NonNull
  private TodoRepository repository;

  public Mono<ServerResponse> create(final ServerRequest request) {
    final Mono<Todo> todoMono = request.bodyToMono(Todo.class);
    return baseResponse().body(repository.create(todoMono), Todo.class);
  }

  public Mono<ServerResponse> get(final ServerRequest request) {
    final Long id = Long.valueOf(request.pathVariable("id"));
    return repository.findById(id).flatMap(todo -> ok().contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(BodyInserters.fromObject(todo))).switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> getAll(final ServerRequest request) {
    return baseResponse().body(repository.all(), Todo.class);
  }

  public Mono<ServerResponse> update(final ServerRequest request) {
    final Long id = Long.valueOf(request.pathVariable("id"));
    final Mono<Todo> updateTodoMono = request.bodyToMono(Todo.class);
    return repository.update(id, updateTodoMono)
        .flatMap(todo -> baseResponse().body(BodyInserters.fromObject(todo)))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> delete(final ServerRequest request) {
    final Long id = Long.valueOf(request.pathVariable("id"));
    return repository.delete(id)
        .flatMap(todo -> baseResponse().body(BodyInserters.fromObject(todo)))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  private BodyBuilder baseResponse() {
    return ok().contentType(MediaType.APPLICATION_JSON_UTF8);
  }
}
