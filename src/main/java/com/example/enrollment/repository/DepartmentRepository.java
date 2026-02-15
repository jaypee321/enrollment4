package com.example.enrollment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enrollment.entity.Department;

@Repository
	public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	    Optional<Department> findByDepartmentCode(String departmentCode);
	}

