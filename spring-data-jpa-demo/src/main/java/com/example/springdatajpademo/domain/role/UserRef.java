package com.example.springdatajpademo.domain.role;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.example.springdatajpademo.domain.role.UserRef.UserRefId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * UserRef
 */
@Entity
@IdClass(UserRefId.class)
@Data
@Accessors(chain = true)
@Table(name = "user_roles")
@EqualsAndHashCode
public class UserRef {
  @ManyToOne
  @Id private Role role;
  @Column(name = "user_id")
  @Id private Long userId;

  @Data
  @NoArgsConstructor
  public static class UserRefId implements Serializable {
    private Role role;
    private Long userId;

    private static final long serialVersionUID = 1L;
  }
}
