package com.example.bookservice.common.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * EventRecordRepository
 */
@Repository
public interface EventRecordRepository extends JpaRepository<EventRecord, Long> {
}
