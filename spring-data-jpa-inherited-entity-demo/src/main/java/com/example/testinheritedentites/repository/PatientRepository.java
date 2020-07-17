package com.example.testinheritedentites.repository;

import java.util.Optional;
import com.example.testinheritedentites.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PatientRepository
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
  public Optional<Patient> findByUsername(final String username);
}
