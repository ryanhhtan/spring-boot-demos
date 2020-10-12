package com.example.bookservice.domain.author.resolver;


import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import com.example.bookservice.domain.author.model.Author;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import lombok.RequiredArgsConstructor;

/**
 * DefaultAuthorResolver
 */
@Component
@RequiredArgsConstructor
public class DefaultAuthorResolver implements AuthorResolver {

  private final RestTemplate rest;

  @Override
  @Cacheable("authors")
  public Author findById(Long id) {
    if (Objects.isNull(id) || id.longValue() < 0) {
      return null;
    }
    return rest.getForObject("http://author-service/authors/{id}", Author.class, id);
  }

  @Override
  public Collection<Author> findById(Collection<Long> ids) {
    if (ids.isEmpty()) {
      return new HashSet<Author>();
    }
    final String idString = ids.stream().map(id -> id.toString()).reduce(null,
        (acc, cur) -> Objects.isNull(acc) ? cur : acc.concat(",").concat(cur));

    return rest.exchange("http://author-service/authors?id.in={ids}", HttpMethod.GET, null,
        new ParameterizedTypeReference<Collection<Author>>() {
        }, idString).getBody();
  }
}
