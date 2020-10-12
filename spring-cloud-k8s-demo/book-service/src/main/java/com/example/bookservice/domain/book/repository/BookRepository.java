package com.example.bookservice.domain.book.repository;


import com.example.bookservice.domain.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* BookRepository
*/
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
