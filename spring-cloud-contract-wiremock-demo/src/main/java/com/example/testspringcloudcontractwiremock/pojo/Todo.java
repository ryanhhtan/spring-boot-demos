package com.example.testspringcloudcontractwiremock.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Todo
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class Todo {
  private Long id;
  private Long userId;
  private String title;
  private Boolean completed;
}
