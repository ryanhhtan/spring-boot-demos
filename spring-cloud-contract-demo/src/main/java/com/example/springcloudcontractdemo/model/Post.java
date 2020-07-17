package com.example.springcloudcontractdemo.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Post
 */
@Data
@Accessors(chain = true)
public class Post {
  private Long id;
  private String title, content;
}
