package com.example.springcloudcontractdemo.controller;

import java.util.List;
import com.example.springcloudcontractdemo.model.Post;
import com.example.springcloudcontractdemo.service.PostService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * PostController
 */
@RestController
@RequiredArgsConstructor
public class PostController {

  @NonNull
  private PostService postService;


  @GetMapping("/posts")
  public List<Post> findAll() {
    return postService.findAll();
  }

  @GetMapping("/posts/{id}")
  public Post findById(@PathVariable(name = "id") final Long id) {
    return postService.findById(id);
  }

  @PostMapping("/posts")
  public Post create(@RequestBody final Post post) {
    return postService.create(post);
  }

  @DeleteMapping("/posts/{id}")
  public void deleteById(@PathVariable final Long id) {
    postService.deleteById(id);
  }
}
