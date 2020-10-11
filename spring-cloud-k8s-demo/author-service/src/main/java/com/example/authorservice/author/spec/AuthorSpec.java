package com.example.authorservice.author.spec;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.example.authorservice.author.exception.QueryParamNotSupportedException;
import com.example.authorservice.author.model.Author;
import org.springframework.data.jpa.domain.Specification;

/**
 * AuthorSepc
 */
public class AuthorSpec {
	private static Map<String, Function<String, Specification<Author>>> specs;

	public static Specification<Author> idIn(final String ids) {
		return (root, query, builder) -> root.get("id").in(parseIdList(ids));
	};

	public static Specification<Author> nameLike(final String name) {
		return (root, query, builder) -> builder.like(root.get("name"), makeSqlLikeString(name));
	}

	private static List<Long> parseIdList(final String ids) {
		return Arrays.asList(ids.split(",")).stream().map(Long::new).collect(Collectors.toList());
	}

	private static String makeSqlLikeString(final String input) {
		return String.format("%s%s%s", "%", input, "%");
	}

	public static Specification<Author> query(Map<String, String> queryParams) throws Exception {
		if (Objects.isNull(specs)) {
			initSpecs();
		}
		if (!specs.keySet().containsAll(queryParams.keySet())) {
			final Set<String> notSupportParams = queryParams.keySet();
			notSupportParams.removeAll(specs.keySet());
			final String notSupportParamsString = notSupportParams.stream().reduce(null,
					(acc, cur) -> Objects.isNull(acc) ? cur : acc.concat(", ").concat(cur));
			throw new QueryParamNotSupportedException(
					"query parameter(s) not supported: " + notSupportParamsString);
		}
		return queryParams.keySet().stream()
				.map(param -> specs.getOrDefault(param, AuthorSpec::noMatch).apply(queryParams.get(param)))
				.reduce(null, (acc, cur) -> Objects.isNull(acc) ? cur : acc.and(cur));
	}


	public static Specification<Author> noMatch(final String param) {
		return null;
	}

	public static void initSpecs() {
		specs = new HashMap<>();
		specs.put("id.in", AuthorSpec::idIn);
		specs.put("name.like", AuthorSpec::nameLike);
	}

}
