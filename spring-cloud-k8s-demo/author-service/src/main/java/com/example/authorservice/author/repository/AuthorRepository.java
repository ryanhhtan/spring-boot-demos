package com.example.authorservice.author.repository;

import com.example.authorservice.author.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
* AuthorRepository
*/
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {
}
