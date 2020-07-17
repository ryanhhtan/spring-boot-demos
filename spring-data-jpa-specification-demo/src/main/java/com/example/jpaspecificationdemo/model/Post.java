package com.example.jpaspecificationdemo.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * Post
 */
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@Table(name = "posts")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Post extends BaseEntity {

  @NotNull
  private String title;

  @NotNull
  private String body;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "post",
      orphanRemoval = true)
  @JsonManagedReference
  private List<Comment> comments;

  public void addComment(final Comment comment) {
    comment.setPost(this);
    comments.add(comment);
  }

  public void removeComment(final Comment comment) {
    comment.setPost(null);
    comments.remove(comment);
  }
}
