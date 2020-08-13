package com.example.springdatajpademo.domain.user;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javassist.NotFoundException;
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

  public User create(final User user) {
    if (!user.getRoles().isEmpty()) {
      user.getRoles().stream().forEach(user::addRole);
    }
    return repository.save(user).resolveRoles();
  }

  public Optional<User> findById(final Long id) {
    return repository.findById(id).map(user -> user.resolveRoles());
  }

  public User update(final Long id, final User update) throws Exception {
    final User user =
        repository.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
    BeanUtils.copyProperties(update, user, "id", "roleRefs", "roles");
    if (Objects.nonNull(update.getRoles())) {
      user.removeAllRoles();
      update.getRoles().forEach(user::addRole);
    }
    return repository.save(user);
  }

  public List<User> findAll() {
    return repository.findAll();
  }

  public void deleteById(final Long id) {
    repository.deleteById(id);
  }
}
