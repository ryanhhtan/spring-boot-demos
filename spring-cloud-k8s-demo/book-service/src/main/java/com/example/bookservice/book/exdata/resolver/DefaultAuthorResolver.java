package com.example.bookservice.book.exdata.resolver;

import java.util.Collection;
import java.util.Objects;
import com.example.bookservice.book.exdata.model.Author;
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
  public Author findById(Long id) {
    return rest.getForObject("http://author-service/{id}", Author.class, id);
  }

  @Override
  public Collection<Author> findById(Collection<Long> ids) {
    final String idString = ids.stream().map(id -> id.toString()).reduce(null,
        (acc, cur) -> Objects.isNull(acc) ? acc : acc.concat(",").concat(cur));
    return rest.exchange("http://author-service?id.in={ids}", HttpMethod.GET, null,
        new ParameterizedTypeReference<Collection<Author>>() {
        }, idString).getBody();


  }
}
