package com.example.springdatajdbcjooqdemo.domain.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * UserService
 */
@Service
@RequiredArgsConstructor
public class UserService {
  @NonNull
  private UserRepository repository;

  @NonNull
  private UserJooqRepository jooqRepository;

  public List<User> findAll() {
    return StreamSupport.stream(repository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  public Optional<User> findById(final Long id) {
    return jooqRepository.findById(id);
  }

  public User create(final User user) {
    return repository.save(user);
  }

  public void deleteById(final Long id) {
    repository.deleteById(id);
  }
}
