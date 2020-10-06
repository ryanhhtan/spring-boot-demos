package com.example.springactuatordemo.greeting.model;

/**
 * Greeting
 */
public class Greeting {
  private String message;

  public Greeting(String message) {
    this.setMessage(message);
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
