package com.example.springdatajpademo.domain.role;

import com.example.springdatajpademo.resolver.EntityResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * RoleRepository
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, EntityResolver<Role, Long>{
}
