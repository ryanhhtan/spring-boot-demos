package com.example.springappeventdemo.domain.foo;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

/**
 * FooServiceImpl
 */
@Service
@RequiredArgsConstructor
public class FooServiceImpl implements FooService, ApplicationEventPublisherAware {
  private ApplicationEventPublisher publisher;
  @Override
  public Foo getFoo(String content) {
    final Foo foo = new Foo(content);
    publisher.publishEvent(new FooEvent(foo).setOperation(FooEvent.Operation.READ));
    return foo;
  }
  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.publisher = applicationEventPublisher;
  }
}
