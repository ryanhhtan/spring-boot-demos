package com.example.springdocsopenapidemo.model;

import com.example.springdocsopenapidemo.jsonviews.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Post
 */
@Data
@Accessors(chain = true)
public class Post {
  @JsonView({Views.Public.class})
  private Long id;
  @JsonView({Views.Public.class, Views.Create.class, Views.Update.class})
  private String title;
  @JsonView({Views.Public.class ,Views.Create.class, Views.Update.class})
  private String content;
}
