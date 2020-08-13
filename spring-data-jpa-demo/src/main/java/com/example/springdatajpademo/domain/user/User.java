package com.example.springdatajpademo.domain.user;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.example.springdatajpademo.domain.role.Role;
import com.example.springdatajpademo.resolver.EntityResolver;
import com.example.springdatajpademo.utils.BeanFinder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * User
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(Include.NON_EMPTY)
@Slf4j
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  private String name;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL,
      orphanRemoval = true)
  @JsonIgnore
  private Set<RoleRef> roleRefs = new HashSet<>();

  public User addRole(final Role role) {
    roleRefs.add(new RoleRef().setUser(this).setRoleId(role.getId()));
    return this;
  }

  public User removeRole(final Role role) {
    roleRefs.remove(new RoleRef().setUser(this).setRoleId(role.getId()));
    return this;
  }

  public User removeAllRoles() {
    if (!roleRefs.isEmpty()) {
      roleRefs.removeAll(
          roleRefs.stream().map(ref -> new RoleRef().setUser(this).setRoleId(ref.getRoleId()))
              .collect(Collectors.toSet()));
    }
    return this;
  }

  @Transient
  private Set<Role> roles;

  public User resolveRoles() {
    final EntityResolver<Role, Long> resolver = BeanFinder.getBean(new ParameterizedTypeReference<EntityResolver<Role, Long>>(){});
    log.info("### {}", resolver);
    if (!roleRefs.isEmpty() && Objects.nonNull(resolver)) {
      roles = new HashSet<>();
      roles = resolver
          .findByIdIn(roleRefs.stream().map(RoleRef::getRoleId).collect(Collectors.toList()))
          .stream().collect(Collectors.toSet());
    }
    return this;
  }
}
