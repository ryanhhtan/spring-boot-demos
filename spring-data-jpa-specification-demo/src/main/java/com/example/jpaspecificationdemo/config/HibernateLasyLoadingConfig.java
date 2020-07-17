package com.example.jpaspecificationdemo.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HibernateLasyLoadingConfig
 */
@Configuration
public class HibernateLasyLoadingConfig {
  @Bean
  public Module hibernate5Module() {
    return new Hibernate5Module();
  }
}
