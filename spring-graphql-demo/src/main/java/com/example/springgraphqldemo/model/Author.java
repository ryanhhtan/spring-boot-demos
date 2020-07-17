package com.example.springgraphqldemo.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * Author
 */
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@Accessors(chain = true)
@Entity
@Table(name = "authors")
public class Author {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String username;

  private String name;

  private String thumbnail;

  @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
  @Builder.Default
  private List<Post> posts =new  ArrayList<>();

  public void addPost(final Post post) {
    posts.add(post);
    post.setAuthor(this);
  }

  public void removePost(final Post post) {
    posts.remove(post);
    post.setAuthor(null);
  }
}
