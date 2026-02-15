package com.example.enrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enrollment.entity.ClassSection;

@Repository
public interface ClassSectionRepository extends JpaRepository<ClassSection, Integer> {
    List<ClassSection> findByTerm_TermId(Integer termId);
    // Find sections for a specific course in a specific term
    List<ClassSection> findByCourse_CourseIdAndTerm_TermId(Integer courseId, Integer termId);
}