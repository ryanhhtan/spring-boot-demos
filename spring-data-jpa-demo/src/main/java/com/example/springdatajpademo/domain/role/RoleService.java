package com.example.springdatajpademo.domain.role;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * RoleService
 */
@Service
@RequiredArgsConstructor
public class RoleService {
  @NonNull
  private RoleRepository repository;

  public Role create(final Role role) {
    final Set<Permission> permissions = role.getPermissions();
    role.setPermissions(new HashSet<>());
    permissions.stream().forEach(role::addPermission);
    return repository.save(role);
  }

  public Optional<Role> findById(final Long id) {
    return repository.findById(id).map(Role::resolveUsers);
  }

  public List<Role> findAll() {
    return repository.findAll();
  }

  public void deleteById(final Long id) {
    repository.deleteById(id);
  }
}
