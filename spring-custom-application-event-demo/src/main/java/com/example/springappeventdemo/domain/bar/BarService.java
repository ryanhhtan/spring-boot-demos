package com.example.springappeventdemo.domain.bar;

import com.example.springappeventdemo.domain.foo.FooEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * BarService
 */
@Slf4j
@Service
public class BarService implements ApplicationListener<FooEvent>{
  @Override
  public void onApplicationEvent(FooEvent event) {
    log.info("op: {}, data: {}", event.getOperation(), event.getSource());
  }
}
