package com.example.enrollment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    @Column(unique = true, length = 20, nullable = false)
    private String courseCode;

    @Column(length = 100, nullable = false)
    private String courseTitle;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer creditUnits;
    private Integer lectureHoursPerWeek = 3;
    private Integer labHoursPerWeek = 0;
    private Integer maxStudents = 40;

    @Column(length = 20)
    private String courseType; // e.g., "Lecture", "Laboratory"

    private Boolean activeStatus = true;
}
