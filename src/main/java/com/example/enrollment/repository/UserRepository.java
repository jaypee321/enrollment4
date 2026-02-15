package com.example.enrollment.repository;

import com.example.enrollment.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Spring creates the SQL for this automatically
    User findByUsername(String username);
    
}