package com.example.springdatajpademo.resolver;

import java.util.List;
import java.util.Optional;

/**
 * EntityResolver
 */
public interface EntityResolver<E, ID> {
  public Optional<E> findById(final ID id);
  public List<E> findByIdIn(final List<ID> id);
}
