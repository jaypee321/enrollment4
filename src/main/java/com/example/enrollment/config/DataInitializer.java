package com.example.enrollment.config;

import com.example.enrollment.entity.User;
import com.example.enrollment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        
        // 1. ADMIN
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ROLE_ADMIN"); // Added ROLE_ prefix for security consistency
            userRepository.save(admin);
            System.out.println("SYSTEM: Admin account ready.");
        }

        // 2. CASHIER (New)
        if (userRepository.findByUsername("cashier") == null) {
            User cashier = new User();
            cashier.setUsername("cashier");
            cashier.setPassword(passwordEncoder.encode("cashier123"));
            cashier.setRole("ROLE_CASHIER");
            userRepository.save(cashier);
            System.out.println("SYSTEM: Cashier account ready.");
        }

        // 3. FACULTY (New)
        if (userRepository.findByUsername("faculty") == null) {
            User faculty = new User();
            faculty.setUsername("faculty");
            faculty.setPassword(passwordEncoder.encode("faculty123"));
            faculty.setRole("ROLE_FACULTY");
            userRepository.save(faculty);
            System.out.println("SYSTEM: Faculty account ready.");
        }

        // 4. DEFAULT STUDENT (For Testing)
        if (userRepository.findByUsername("student") == null) {
            User student = new User();
            student.setUsername("student");
            student.setPassword(passwordEncoder.encode("student123"));
            student.setRole("ROLE_STUDENT");
            userRepository.save(student);
            System.out.println("SYSTEM: Student test account ready.");
        }
    }
}
