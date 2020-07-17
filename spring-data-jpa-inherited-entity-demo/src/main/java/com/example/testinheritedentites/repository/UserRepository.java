package com.example.testinheritedentites.repository;

import java.util.Optional;
import com.example.testinheritedentites.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  public Optional<User> findByUsername(final String username);

  @Procedure(procedureName = "setUserType")
  public void setUserType(final Long id, final String type);
}
