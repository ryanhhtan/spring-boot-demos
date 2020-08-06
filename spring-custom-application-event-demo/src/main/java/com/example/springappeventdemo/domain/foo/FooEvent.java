package com.example.springappeventdemo.domain.foo;

import org.springframework.context.ApplicationEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * FooEvent
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FooEvent extends ApplicationEvent {
  private static final long serialVersionUID = 1L;
  private Operation operation;
  public FooEvent(Object source) {
    super(source);
  }

  public enum  Operation {
    CREATE,
    READ,
    UPDATE,
    DELETE
  }
}
