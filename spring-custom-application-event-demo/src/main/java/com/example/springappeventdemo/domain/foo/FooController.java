package com.example.springappeventdemo.domain.foo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * FooController
 */
@RestController
@RequiredArgsConstructor
public class FooController {
  @NonNull
  private FooService service;

  @GetMapping("/foo/{content}")
  public Foo getFoo(@PathVariable final String content) {
    return service.getFoo(content);
  }
}
