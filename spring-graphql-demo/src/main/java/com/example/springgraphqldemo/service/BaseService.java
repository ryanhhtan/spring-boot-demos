package com.example.springgraphqldemo.service;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * BaseService
 */
public abstract class BaseService {

  protected String getCurrentUsername() {
    return SecurityContextHolder.getContext().getAuthentication().getName();
  }
}
