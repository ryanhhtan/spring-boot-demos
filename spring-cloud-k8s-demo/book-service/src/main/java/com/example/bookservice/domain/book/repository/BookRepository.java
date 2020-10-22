package com.example.bookservice.domain.book.repository;


import java.util.List;
import com.example.bookservice.domain.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * BookRepository
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
  @Query("SELECT b from Book b JOIN AuthorRef ar ON b.id = ar.book WHERE ar.authorId = ?1")
  public List<Book> findByAuthorId(Long authorId);
}
