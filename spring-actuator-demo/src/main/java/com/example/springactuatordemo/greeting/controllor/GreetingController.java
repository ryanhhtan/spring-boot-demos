package com.example.springactuatordemo.greeting.controllor;

import com.example.springactuatordemo.greeting.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GreetingController
 */
@RestController
@RequestMapping("/greeting")
public class GreetingController {
  @GetMapping
  public Greeting greet() {
    return new Greeting("Hello world");
  }
}
