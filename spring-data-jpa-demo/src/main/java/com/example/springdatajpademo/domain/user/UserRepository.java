package com.example.springdatajpademo.domain.user;

import com.example.springdatajpademo.resolver.EntityResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, EntityResolver<User, Long> {
}
