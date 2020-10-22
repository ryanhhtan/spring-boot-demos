package com.example.authorservice.domain.author.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.example.authorservice.common.Domain;
import lombok.Getter;
import lombok.Setter;

/**
 * Author
 */
@Entity
@Table(name = "authors")
@Getter
public class Author extends Domain {
	Author() {
	}

	Author(final String name) {
		assert Objects.nonNull(name) && !name.trim().isEmpty() : "can not create user without a name";
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	private String name;
}
