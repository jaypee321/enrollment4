package com.example.enrollment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", "/login", "/css/**", "/images/**", "/js/**",
                    "/enroll", "/personal-information", "/contact-info",
                    "/family-info", "/educational-bg", "/requirements",
                    "/submit-enrollment", "/account_status", "/status"
                ).permitAll()
                // Ensure specific roles can access their specific pages
                .requestMatchers("/admin/dashboard").hasRole("ADMIN")
                .requestMatchers("/admin/walkin-payment").hasAnyRole("ADMIN", "CASHIER") 
                .requestMatchers("/admin/cashier").hasAnyRole("CASHIER", "ADMIN", "FACULTY") // Added per your request
                .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.disable())
            .formLogin(form -> form
                .loginPage("/login")
                .successHandler(roleBasedSuccessHandler())
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler roleBasedSuccessHandler() {
        return (request, response, authentication) -> {
            var roles = authentication.getAuthorities().stream()
                    .map(auth -> auth.getAuthority())
                    .toList();

            // Debug print to console so you can see exactly what role is being detected
            System.out.println("User logged in with roles: " + roles);

            if (roles.contains("ROLE_ADMIN")) {
                response.sendRedirect("/admin/dashboard");
            } else if (roles.contains("ROLE_CASHIER")) {
                response.sendRedirect("/admin/walkin-payment"); 
            } else if (roles.contains("ROLE_FACULTY")) {
                // Redirects to the path you specified
                response.sendRedirect("/admin/cashier");
            } else {
                response.sendRedirect("/index");
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}