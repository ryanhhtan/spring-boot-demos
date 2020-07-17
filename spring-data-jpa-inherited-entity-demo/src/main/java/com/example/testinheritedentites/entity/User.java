package com.example.testinheritedentites.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * User
 */

@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@ToString(callSuper = true)
@Accessors(chain = true)
public class User extends BaseEntity {
  public static enum Type {
    unspecified, doctor, patient
  }

  @NaturalId
  @EqualsAndHashCode.Include
  protected String username;

  @Builder.Default
  @Enumerated(EnumType.STRING)
  private Type type = Type.unspecified;
}
