package com.example.enrollment.repository; 

import com.example.enrollment.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List; // Import List

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByStudentNumber(String studentNumber);
    
    List<Student> findByLastNameIgnoreCase(String lastName);

	List<Student> findByStudentNumberContainingOrLastNameContainingIgnoreCase(String keyword, String keyword2);

	List<Student> findByLastNameContainingIgnoreCase(String trim);
    }