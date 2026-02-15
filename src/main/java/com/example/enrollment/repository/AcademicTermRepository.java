package com.example.enrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enrollment.entity.AcademicTerm;

@Repository
public interface AcademicTermRepository extends JpaRepository<AcademicTerm, Integer> {
    List<AcademicTerm> findByStatus(String status);
}
