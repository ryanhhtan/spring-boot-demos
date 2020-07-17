package com.example.webfluxdemo.config;

import java.security.interfaces.RSAPublicKey;
import org.mydevtools.mocktools.jwt.MockJwtGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * SecurityConfig
 */
@EnableWebFluxSecurity
public class SecurityConfig  {

  @Bean
  public RSAPublicKey publicKey() {
    return (RSAPublicKey) new MockJwtGenerator().getKeyPair().getPublic();
  } 

  @Bean
  SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {
    http
      .csrf().disable()
      .authorizeExchange()
      .anyExchange().authenticated()
      .and()
      .oauth2ResourceServer()
      .jwt()
      .publicKey(publicKey())
      ;
    return http.build();
  }
}
