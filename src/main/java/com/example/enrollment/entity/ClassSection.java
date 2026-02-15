package com.example.enrollment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "class_sections", 
       uniqueConstraints = {@UniqueConstraint(columnNames = {"term_id", "course_id", "section_code"})})
public class ClassSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sectionId;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "term_id", nullable = false)
    private AcademicTerm term;

    @Column(length = 10, nullable = false)
    private String sectionCode;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    private Integer maxCapacity;

    @Column(length = 20)
    private String sectionStatus = "Planning";

	public void setSectionStatus(String string) {
		// TODO Auto-generated method stub
		
	}

	public Object getFaculty() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFaculty(Faculty faculty2) {
		// TODO Auto-generated method stub
		
	}
}
