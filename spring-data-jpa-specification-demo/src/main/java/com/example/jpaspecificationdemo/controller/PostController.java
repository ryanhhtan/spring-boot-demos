package com.example.jpaspecificationdemo.controller;

import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import com.example.jpaspecificationdemo.model.Comment;
import com.example.jpaspecificationdemo.model.Post;
import com.example.jpaspecificationdemo.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * PostController
 */
@RequiredArgsConstructor
@RestController
@Slf4j
public class PostController {
  @NonNull
  private PostService postService;

  @PostMapping("/posts")
  public Post createPost(@RequestBody @Valid final Post post) {
    return postService.create(post);
  }


  @GetMapping("/posts")
  public List<Post> findAll(@RequestParam(required = false, name = "commentedBy") final String commentedBy) {
    log.info("### query: {}", commentedBy);
    if (Objects.isNull(commentedBy)) {
      return postService.findAll();
    }
    return postService.findByCommentor(commentedBy);
  }

  @PostMapping("/posts/{postId}/comments")
  public Comment comment(@PathVariable(name = "postId") Long postId,
      @RequestBody @Valid final Comment comment) throws Exception {
      log.info("### postId: {}", postId);
      log.info("### comment: {}", comment);
    return postService.comment(postId, comment);
  }
}
