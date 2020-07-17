package com.example.springgraphqldemo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.springgraphqldemo.model.Author;
import com.example.springgraphqldemo.repository.AuthorRepository;
import com.example.springgraphqldemo.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * AuthorService
 */
@Service
@RequiredArgsConstructor
public class AuthorService extends BaseService {

  @NonNull
  private AuthorRepository repository;

  public List<Author> findAll(final Integer queryCount, final Long queryOffset) {
    final Integer count = queryCount == null ? 10 : queryCount;
    final Long offset = queryOffset == null ? 0 : queryOffset;
    return repository.findAll().stream().filter(author -> author.getId() > offset).limit(count)
        .collect(Collectors.toList());
  }


  public Optional<Author> findByIdOptional(final Long id) {
    return repository.findById(id);
  }

  public Author updateAuthor(final Author toUpdate) {
    final Author origin = findByUsername(getCurrentUsername());
    BeanUtils.copyProperties(toUpdate, origin, ObjectUtils.filterNullFields(toUpdate));
    return repository.save(origin);
  }

  public Author findByUsername(final String username) {
    return repository.findByUsername(username).orElseGet(
        () -> Author.builder().username(username).thumbnail("https://au.ed/default.png").build());
  }

  public Author findWithPostsByUsername(final String username) {
    return repository.findWithPostsByUsername(username).get();
  }

  public Author save(final Author author) {
    return repository.save(author);
  }
}
