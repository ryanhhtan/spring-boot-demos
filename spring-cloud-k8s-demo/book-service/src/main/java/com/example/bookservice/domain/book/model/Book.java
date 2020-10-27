package com.example.bookservice.domain.book.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.example.bookservice.common.event.Domain;
import com.example.bookservice.domain.author.model.Author;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * Book
 */
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "books")
@Slf4j
public class Book extends Domain {
  Book() {
  }

  Book(final String title) {
    assert Objects.nonNull(title) : "title cannot be null";
    setTitle(title);
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "book", cascade = CascadeType.ALL,
      orphanRemoval = true)
  private Set<AuthorRef> authorRefs = new HashSet<>();

  public Book addAuthor(final Author author) {
    assert Objects.nonNull(author.getId()) : "author.id cannot be null";
    authorRefs.add(new AuthorRef(this, author.getId()));
    return this;
  }

  public Book deleteAuthor(final Author author) {
    log.info("*** author: {}", author);
    final Set<AuthorRef> refs = authorRefs.stream()
        .filter(ref -> ref.getAuthorId() == author.getId()).collect(Collectors.toSet());
    log.info("*** refs.size: {}", refs.size());
    authorRefs.removeAll(refs);
    refs.forEach(it -> it.setBook(null));
    return this;
  }

  public Book addAuthors(final Collection<Author> authors) {
    final Set<AuthorRef> refs = authors.stream().filter(author -> Objects.nonNull(author.getId()))
        .map(author -> new AuthorRef(this, author.getId())).collect(Collectors.toSet());
    if (refs.size() != authors.size()) {
      throw new IllegalArgumentException("author.id cannot be null");
    }
    authorRefs.addAll(refs);
    return this;
  }

  public Book deleteAuthors(final Collection<Author> authors) {
    authors.stream().forEach(this::deleteAuthor);
    return this;
  }

  public Book deleteAllAuthors() {
    authorRefs.clear();
    return this;
  }
}
