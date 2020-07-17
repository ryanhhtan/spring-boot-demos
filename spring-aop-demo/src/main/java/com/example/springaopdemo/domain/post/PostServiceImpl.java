package com.example.springaopdemo.domain.post;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javassist.NotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * PostServiceImpl
 */
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
  @NonNull
  private PostRepository postRepository;

  @Override
  public List<Post> findAll() {
    return postRepository.findAll();
  }

  @Override
  public Post create(Post post) {
    return postRepository.save(post);
  }

  @Override
  public Optional<Post> findById(Long id) {
    return postRepository.findById(id);
  }

  @Override
  public Post update(Long id, Post update) throws NotFoundException {
    final Post target = findById(id).orElseThrow(() -> new NotFoundException("no post found"));
    update.setId(id);
    BeanUtils.copyProperties(update, target);
    return postRepository.save(target);
  }

  @Override
  public void delete(Long id) throws NotFoundException {
    postRepository.deleteById(id);
  }
}
