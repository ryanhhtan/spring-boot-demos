package com.example.springdatajdbcjooqdemo.domain.user;

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
 * UserController
 */
@RestController
@RequiredArgsConstructor
public class UserController {
  @NonNull
  private UserService service;

    @GetMapping("/users")
    public List<User> findAll() {
      return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable(name = "id") final Long id) throws Exception {
      return service.findById(id).orElseThrow(() -> new Exception("user not found"));
    }

    @PostMapping("/users")
    public User create(@RequestBody final User user) {
      return service.create(user);
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable(name = "id") final Long id) {
      service.deleteById(id);
    }
}
