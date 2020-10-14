package com.example.bookservice.domain.author.model;

import com.example.bookservice.common.WithId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* Author
*/
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Author implements WithId<Long> {
	@EqualsAndHashCode.Include
	private Long id;
	private String name;
}
