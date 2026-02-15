package com.example.enrollment.entity;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "academic_terms")
public class AcademicTerm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer termId;

    @Column(length = 20, nullable = false)
    private String termCode;

    @Column(length = 50)
    private String termName;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(length = 20)
    private String status; // e.g., "Planning", "Active"

    // --- SAFETY HOOK: Prevents "Zero Date" errors in MySQL ---
    @PrePersist
    protected void onCreate() {
        if (this.startDate == null) {
            // Default to August 1st, 2024
            this.startDate = LocalDate.of(2024, 8, 1);
        }
        if (this.endDate == null) {
            // Default to December 20th, 2024
            this.endDate = LocalDate.of(2024, 12, 20);
        }
        if (this.status == null) {
            this.status = "Active";
        }
    }

    // --- GETTERS AND SETTERS (Required) ---
    public Integer getTermId() { return termId; }
    public void setTermId(Integer termId) { this.termId = termId; }

    public String getTermCode() { return termCode; }
    public void setTermCode(String termCode) { this.termCode = termCode; }

    public String getTermName() { return termName; }
    public void setTermName(String termName) { this.termName = termName; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}