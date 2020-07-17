package com.example.springcloudcontractdemo.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * AopConfig
 */
@Aspect
@Configuration
public class AopConfig {
  private Logger logger = LoggerFactory.getLogger(getClass());

  @Around("execution(* com.example.springcloudcontractdemo.*.* (..))")
  public void logExcutionTime(JoinPoint joinPoint) {
    logger.info("#### join point: {}", joinPoint);
  }

  
}
