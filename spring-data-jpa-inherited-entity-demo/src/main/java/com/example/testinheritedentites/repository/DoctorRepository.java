package com.example.testinheritedentites.repository;

import java.util.Optional;
import com.example.testinheritedentites.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DoctorRepository
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
  public Optional<Doctor> findByUsername(final String username);
}
