package com.example.springdatajpademo.domain.role;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * RoleController
 */
@RestController
@RequiredArgsConstructor
public class RoleController {

  @NonNull
  private RoleService service;

  @GetMapping("/roles")
  public List<Role> findAll() {
    return service.findAll();
  }

  @GetMapping("/roles/{id}")
  public Role findById(@PathVariable(name = "id") final Long id) throws Exception {
    return service.findById(id).orElseThrow(() -> new Exception());
  }

  @PostMapping("/roles")
  public Role create(@RequestBody final Role role) {
    return service.create(role);
  }

  @DeleteMapping("/roles/{id}")
  public void delete(@PathVariable(name = "id") final Long id) {
    service.deleteById(id);
  }
}
