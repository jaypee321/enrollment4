package com.example.enrollment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enrollment.entity.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    List<Faculty> findByDepartment_DepartmentId(Integer departmentId);
    Optional<Faculty> findByEmployeeNumber(String employeeNumber);
}
