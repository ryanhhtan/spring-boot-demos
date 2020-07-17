package com.example.jpaspecificationdemo.service;

import java.util.List;
import javax.transaction.Transactional;
import com.example.jpaspecificationdemo.model.Comment;
import com.example.jpaspecificationdemo.model.Post;
import com.example.jpaspecificationdemo.repository.PostRepository;
import com.example.jpaspecificationdemo.spec.PostSpec;
import org.springframework.stereotype.Service;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * PostService
 */
@Service
@RequiredArgsConstructor
public class PostService {
  @NonNull
  private PostRepository postRepository;

  public Post create(final Post post) {
    return postRepository.save(post);
  }

  public List<Post> findAll() {
    return postRepository.findAll();
  }

  public List<Post> searchFor(final String text) {
    return postRepository.findAll(PostSpec.titleContains(text).or(PostSpec.bodyContains(text)));
  }

  public List<Post> findByCommentor(final String name) {
    return postRepository.findAll(PostSpec.commentedBy(name));
  }

  @Transactional
  public Comment comment(final Long postId, final Comment comment) throws Exception {
    final Post post =
        postRepository.findById(postId).orElseThrow(() -> new Exception("Post not found"));
    post.addComment(comment);
    return comment;
  }
}
