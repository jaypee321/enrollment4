package com.example.enrollment.service;

import com.example.enrollment.entity.Student;
import com.example.enrollment.repository.PaymentRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FinancialService {

    private final JdbcTemplate jdbcTemplate;
    private final PaymentRepository paymentRepository;

    public FinancialService(JdbcTemplate jdbcTemplate, PaymentRepository paymentRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.paymentRepository = paymentRepository;
    }

    public void populateStudentFinancialData(Student student, Model model) {
        // 1. Fetch total units
        Integer totalUnitsFromDb = jdbcTemplate.queryForObject(
            "SELECT COALESCE(SUM(c.credit_units), 0) FROM student_enlistments se " +
            "JOIN courses c ON se.course_id = c.course_id WHERE se.student_id = ?",
            Integer.class, student.getId());
        
        int totalUnits = (totalUnitsFromDb != null) ? totalUnitsFromDb : 0;
        
        // --- ADDED: 24-UNIT CAP LOGIC ---
        int unitsToCharge = Math.min(totalUnits, 24); 
        
        
     // 2. Fees Calculation
        double ratePerUnit = 1500.00;
        double tuitionFee = unitsToCharge * ratePerUnit; // Capped tuition

        // NEW: Only apply Misc and Other fees if the student actually enrolled in subjects
        double miscTotal = (totalUnits > 0) ? 7431.00 : 0.00;
        double otherFeesTotal = (totalUnits > 0) ? 18562.00 : 0.00;

        // 3. Total Assessment
        double totalAssessment = tuitionFee + miscTotal + otherFeesTotal;

        // ---> FIXED: 4. Get Payments (ONLY DEDUCT TUITION FEES FROM BALANCE) <---
        Double tuitionPaymentsFromDb = jdbcTemplate.queryForObject(
            "SELECT SUM(amount) FROM payments WHERE reference_number = ? " +
            "AND (remarks = 'Tuition Fee' OR remarks IS NULL OR remarks = '')", 
            Double.class, student.getStudentNumber());
            
        double totalPaid = (tuitionPaymentsFromDb != null) ? tuitionPaymentsFromDb : 0.00;
        double outstandingBalance = totalAssessment - totalPaid;

        // 5. INSTALLMENT LOGIC (8 Installments)
        double downpaymentFixed = 3000.00;
        double remainingForInstallments = totalAssessment - downpaymentFixed;
        
        List<Map<String, Object>> installmentSchedule = new ArrayList<>();
        double installmentAmount = (remainingForInstallments > 0) ? (remainingForInstallments / 8) : 0;

        String[] dueDates = {
            "Aug. 30, 2026", "Sep. 15, 2026", "Sep. 30, 2026", "Oct. 15, 2026", 
            "Oct. 30, 2026", "Nov. 15, 2026", "Nov. 30, 2026", "Dec. 10, 2026"
        };
        String[] labels = {
            "1st Installment", "2nd Installment", "3rd Installment", "4th Installment",
            "5th Installment", "6th Installment", "7th Installment", "8th Installment"
        };

        for (int i = 0; i < 8; i++) {
            Map<String, Object> installment = new HashMap<>();
            installment.put("label", labels[i]);
            installment.put("dueDate", dueDates[i]);
            installment.put("amount", installmentAmount);
            
            // The threshold increases with each installment
            double threshold = downpaymentFixed + (installmentAmount * (i + 1));
            
            // Precision fix: using -0.01 to handle small rounding differences in double math
            installment.put("status", totalPaid >= (threshold - 0.01) ? "PAID" : "UNPAID");
            installmentSchedule.add(installment);
        }

        // 6. Populate Model
        model.addAttribute("totalUnits", totalUnits); // Show actual load
        model.addAttribute("unitsCharged", unitsToCharge); // Show capped units
        model.addAttribute("tuitionTotal", tuitionFee);
        model.addAttribute("totalAssessment", totalAssessment);
        model.addAttribute("totalOnlinePayments", totalPaid);
        model.addAttribute("outstandingBalance", outstandingBalance);

        model.addAttribute("downpaymentAmount", downpaymentFixed);
        model.addAttribute("downpaymentStatus", totalPaid >= downpaymentFixed ? "PAID" : "UNPAID");
        model.addAttribute("installments", installmentSchedule);

        // ---> FIXED: 7. Enlisted Subjects Query (Now uses se.section_id and COALESCE TBA) <---
        List<Map<String, Object>> enlistedSubjects = jdbcTemplate.queryForList(
            "SELECT se.enlistment_id, c.course_code, c.course_title, c.credit_units, " +
            "COALESCE(GROUP_CONCAT(CONCAT(" +
            "  CASE sch.day_of_week " +
            "    WHEN 1 THEN 'Mon' WHEN 2 THEN 'Tue' WHEN 3 THEN 'Wed' " +
            "    WHEN 4 THEN 'Thu' WHEN 5 THEN 'Fri' WHEN 6 THEN 'Sat' WHEN 7 THEN 'Sun' ELSE '' " +
            "  END, ' ', " +
            "  TIME_FORMAT(sch.start_time, '%h:%i %p'), '-', TIME_FORMAT(sch.end_time, '%h:%i %p')) " +
            "  SEPARATOR ', '), 'TBA') as schedule " +
            "FROM student_enlistments se " +
            "JOIN courses c ON se.course_id = c.course_id " +
            "LEFT JOIN class_sections cs ON se.section_id = cs.section_id " +  // <-- This fixes the schedules!
            "LEFT JOIN class_schedules sch ON cs.section_id = sch.section_id " +
            "WHERE se.student_id = ? " +
            "GROUP BY se.enlistment_id", 
            student.getId());

        model.addAttribute("enlistedSubjects", enlistedSubjects);
        
        // 8. Payment History (Naturally displays ALL transactions, including parking/documents)
        List<Map<String, Object>> paymentHistory = jdbcTemplate.queryForList(
            "SELECT transaction_id, amount, payment_method, payment_date, remarks FROM payments " +
            "WHERE reference_number = ? ORDER BY payment_date DESC",
            student.getStudentNumber());
            
        model.addAttribute("paymentHistory", paymentHistory);
    }
}