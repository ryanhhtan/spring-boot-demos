package com.example.springaopdemo.aop;

import java.time.Instant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

/**
 * PerformanceTrackAop
 */
@Component
@Aspect
@Slf4j
@Profile("debug")
public class PerformanceTrackAop {
  @Around("execution(* com.example.springaopdemo..*(..))")
  public Object trackPerformance(final ProceedingJoinPoint joinPoint) throws Throwable {
    final Instant start = Instant.now();
    log.info("### {} starting time: {}", joinPoint, start);
    final Object result = joinPoint.proceed();
    final Instant end = Instant.now();
    log.info("### {} ended time: {}", joinPoint, end);
    log.info("### {} executing time: {} us", joinPoint, end.compareTo(start) / 1000);
    return result;
  }
}
