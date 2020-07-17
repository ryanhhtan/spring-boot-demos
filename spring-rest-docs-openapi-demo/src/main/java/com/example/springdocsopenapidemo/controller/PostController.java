package com.example.springdocsopenapidemo.controller;

import com.example.springdocsopenapidemo.jsonviews.Views;
import com.example.springdocsopenapidemo.model.Post;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * PostController
 */
@RestController
public class PostController {

  @PostMapping("/posts")
  @JsonView(Views.Public.class)
  public Post create(@RequestBody @JsonView(Views.Create.class) final Post post) {
    return post.setId(1L);
  }
}
