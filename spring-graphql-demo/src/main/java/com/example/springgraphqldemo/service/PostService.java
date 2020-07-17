package com.example.springgraphqldemo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.springgraphqldemo.model.Author;
import com.example.springgraphqldemo.model.Post;
import com.example.springgraphqldemo.repository.PostRepository;
import com.example.springgraphqldemo.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * PostService
 */
@Service
@RequiredArgsConstructor
public class PostService extends BaseService {

  @NonNull
  private AuthorService authorService;

  @NonNull
  private PostRepository postRepository;

  public Post createPost(final Post post) {
    final Author author = authorService.findWithPostsByUsername(getCurrentUsername());
    post.setAuthor(author);
    return postRepository.save(post);
  }

  public List<Post> findAll(final Integer queryCount, final Long queryOffset) {
    final Integer count = queryCount == null ? 10 : queryCount;
    final Long offset = queryOffset == null ? 0 : queryOffset;
    return postRepository.findAll().stream().filter(post -> post.getId() > offset).limit(count).collect(Collectors.toList());
  }

  public Optional<Post> findByIdOptional(final Long id) {
    return postRepository.findById(id);
  }

  public Post updatePost(final Long id, final Post toUpdate) {
    final Post origin = findById(id);
    BeanUtils.copyProperties(toUpdate, origin, ObjectUtils.filterNullFields(toUpdate));
    return postRepository.save(origin);
  } 
  
  public Post findById(final Long id) {
    return postRepository.findById(id).get();
  } 

  public List<Post> findByAuthor(final Author author) {
    return postRepository.findByAuthor(author);
  }

}
