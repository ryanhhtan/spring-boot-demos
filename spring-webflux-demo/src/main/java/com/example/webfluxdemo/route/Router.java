package com.example.webfluxdemo.route;

import com.example.webfluxdemo.handler.TodoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Router
 */
@Component
@RequiredArgsConstructor
public class Router {

  @NonNull
  private TodoHandler todoHandler;

  @Bean
  public RouterFunction<ServerResponse> todoRouterFunction() {
    return RouterFunctions.route()
      .path("/todos", builder -> builder
          .POST("", todoHandler::create)
          .GET("/{id}", todoHandler::get)
          .GET("", todoHandler::getAll)
          .PUT("/{id}", todoHandler::update)
          .DELETE("/{id}", todoHandler::delete)
          )
      .build();
  }
}
