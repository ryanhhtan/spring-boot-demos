package com.example.springdatajdbcjooqdemo.domain.user;

import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * User
 */
@Table("users")
@Data
@Accessors(chain = true)
public class User {
  @Id
  private Long id;
  private String name;
  @Version
  private Long version;

  @MappedCollection(idColumn = "user_id")
  private Set<Address> addresses = new HashSet<>();
}
