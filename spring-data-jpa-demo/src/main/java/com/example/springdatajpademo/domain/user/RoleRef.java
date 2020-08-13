package com.example.springdatajpademo.domain.user;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.example.springdatajpademo.domain.user.RoleRef.RoleRefId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * RoleRef
 */
@Entity
@IdClass(RoleRefId.class)
@Data
@Accessors(chain = true)
@Table(name = "user_roles")
@EqualsAndHashCode
public class RoleRef {
  @ManyToOne
  @Id private User user;
  @Column(name = "role_id")
  @Id private Long roleId;

  @NoArgsConstructor
  @Data
  public static class RoleRefId implements Serializable {
    private static final long serialVersionUID = 5213327070864737798L;
    private User user;
    private Long roleId;
  }
}
