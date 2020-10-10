package com.example.bookservice.book.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
* AuthorRef
*/
@Entity
@Data
@IdClass(AuthorRef.ID.class)
@Table(name = "author_refs")
@AllArgsConstructor
public class AuthorRef {


	@Id
	@ManyToOne
	private Book book;
	@Id
	private Long authorId;

	@Data
	public static class ID implements Serializable {
    private static final long serialVersionUID = 1L;
    private Book book;
		private Long authorId;
	}
}
