package com.example.springdatajpademo.domain.role;

import java.util.HashSet;
import java.util.List;
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
import com.example.springdatajpademo.domain.user.User;
import com.example.springdatajpademo.resolver.EntityResolver;
import com.example.springdatajpademo.utils.BeanFinder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Role
 */
@Entity
@Table(name = "roles")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(Include.NON_EMPTY)
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;
  private String name;

  @JsonIgnore
  @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, orphanRemoval = true)
  private Set<UserRef> userRefs = new HashSet<>();

  @Transient
  private List<User> users;

  @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private Set<Permission> permissions = new HashSet<>();
  
  public Role addPermission(final Permission permission) {
    permission.setRole(this);
    permissions.add(permission);
    return this;
  }

  public Role removePermission(final Permission permission) {
    permissions.remove(permission);
    permission.setRole(null);
    return this;
  } 


  public Role resolveUsers() {
    final EntityResolver<User, Long> resolver =
        BeanFinder.getBean(new ParameterizedTypeReference<EntityResolver<User, Long>>(){});
    if (!userRefs.isEmpty()) {
      users = resolver
          .findByIdIn(userRefs.stream().map(UserRef::getUserId).collect(Collectors.toList()));
    }
    return this;
  }
}
