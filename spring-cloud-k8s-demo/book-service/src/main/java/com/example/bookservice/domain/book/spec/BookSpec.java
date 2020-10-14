package com.example.bookservice.domain.book.spec;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.example.bookservice.common.SqlUtils;
import com.example.bookservice.common.exception.QueryParamNotSupporttedException;
import com.example.bookservice.domain.book.model.Book;
import org.springframework.data.jpa.domain.Specification;

/**
 * BookSpec
 */
public class BookSpec {

  public static Map<String, Function<String, Specification<Book>>> specs;

  public static Specification<Book> titleLike(final String text) {
    return (root, query, builder) -> builder.like(root.get("title"),
        SqlUtils.blurSearchString(text));
  }

  public static Specification<Book> query(final Map<String, String> queryParams) {
    if (Objects.isNull(specs)) {
      initSpecs();
    }

    if (!specs.keySet().containsAll(queryParams.keySet())) {
      queryParams.keySet().removeAll(specs.keySet());
      final String invalidParams = queryParams.keySet().stream().reduce(null,
          (acc, cur) -> Objects.isNull(acc) ? cur : acc.concat(", ").concat(cur));
      throw new QueryParamNotSupporttedException("query param(s) not supported: " + invalidParams);
    }

    return queryParams.keySet().stream()
        .map(param -> specs.getOrDefault(param, BookSpec::noMatch).apply(queryParams.get(param)))
        .filter(Objects::nonNull)
        .reduce(null, (acc, cur) -> Objects.isNull(acc) ? cur : acc.and(cur));
  }

  public static Specification<Book> idIn(final String ids) {
    return (root, query, builder) -> root.get("id").in(parseIds(ids));
  }

  public static Specification<Book> noMatch(final String param) {
    return null;
  }

  private static void initSpecs() {
    specs = new HashMap<>();
    specs.put("title.like", BookSpec::titleLike);
    specs.put("id.in", BookSpec::idIn);
  }

  private static List<Long> parseIds(final String ids) {
    return Arrays.asList(ids.split(",")).stream().map(Long::new).collect(Collectors.toList());
  }
}
