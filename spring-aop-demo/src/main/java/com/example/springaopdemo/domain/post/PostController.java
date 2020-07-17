package com.example.springaopdemo.domain.post;

import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javassist.NotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * PostController
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
  @NonNull
  private PostService postService;

  @GetMapping
  public List<Post> findAll() {
    return postService.findAll();
  }

  @PostMapping
  public Post create(@RequestBody @Valid final Post post) {
    return postService.create(post);
  }

  @GetMapping("/{id}")
  public Post findById(@PathVariable(name = "id") final Long id) throws Exception {
    return postService.findById(id).orElseThrow(() -> new NotFoundException("post not found"));
  }

  @PutMapping("/{id}")
  public Post update(@PathVariable(name = "id") final Long id,
      @RequestBody @Valid final Post update) throws Exception {
    return postService.update(id, update);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable(name = "id") final Long id) throws Exception {
    postService.delete(id);
  }



}
