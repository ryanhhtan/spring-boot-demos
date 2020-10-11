package com.example.authorservice.author.spec;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
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

	private static List<Long> parseIdList(final String ids) {
		return Arrays.asList(ids.split(",")).stream().map(Long::new).collect(Collectors.toList());
	}

	public static Specification<Author> query(Map<String, String> queryParams) {
		if (Objects.isNull(specs)) {
			initSpecs();
		}
		return queryParams.keySet().stream()
				.map(param -> specs.get(param).apply(queryParams.get(param)))
				.reduce(null, (acc, cur) -> Objects.isNull(acc) ? cur : acc.and(cur));
	}

	public static void initSpecs() {
		specs = new HashMap<>();
		specs.put("id.in", AuthorSpec::idIn);
	}
}
