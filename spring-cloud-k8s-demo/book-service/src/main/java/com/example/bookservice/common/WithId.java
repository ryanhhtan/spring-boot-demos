package com.example.bookservice.common;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * WithId
 */
public interface WithId<T> {
  T getId();

  static <ID> Set<ID> extractIds(final Collection<? extends WithId<ID>> items) {
    if (Objects.isNull(items)) {
      return null;
    }
    return items.stream().map(it -> it.getId()).collect(Collectors.toSet());
  }

  static <ID> String extractIdsString(final Collection<? extends WithId<ID>> items) {
    if (Objects.isNull(items)) {
      return null;
    }
    return items.stream().map(it -> it.getId().toString()).reduce(null,
        (acc, cur) -> Objects.isNull(acc) ? cur : acc.concat(", ").concat(cur));
  }
}
