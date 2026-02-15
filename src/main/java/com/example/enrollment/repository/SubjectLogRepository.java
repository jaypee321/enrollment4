package com.example.enrollment.repository;

import com.example.enrollment.entity.SubjectLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubjectLogRepository extends JpaRepository<SubjectLog, Long> {
    // Helper to find logs for a specific student if needed later
    List<SubjectLog> findByStudentNumberOrderByTimestampDesc(String studentNumber);
    
    // Default find all ordered by time
    List<SubjectLog> findAllByOrderByTimestampDesc();
}