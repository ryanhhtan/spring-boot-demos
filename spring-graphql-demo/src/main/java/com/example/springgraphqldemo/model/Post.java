package com.example.springgraphqldemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@EqualsAndHashCode
@Accessors(chain = true)
@Entity
@Table(name = "posts")
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String text;
  private String category;
  
  @ManyToOne
  private Author author;
}
