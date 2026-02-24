package com.example.enrollment.controller;

import com.example.enrollment.entity.Payment;
import com.example.enrollment.entity.Student;
import com.example.enrollment.repository.PaymentRepository;
import com.example.enrollment.repository.StudentRepository;
import com.example.enrollment.service.FinancialService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.example.enrollment.entity.SubjectLog;
import com.example.enrollment.repository.SubjectLogRepository;

@Controller
@RequestMapping("/admin") 
public class AdminController {

    private final StudentRepository studentRepository;
    private final PaymentRepository paymentRepository;
    private final SubjectLogRepository subjectLogRepository;
    private final JdbcTemplate jdbcTemplate;
    private final FinancialService financialService;

    public AdminController(StudentRepository studentRepository, 
                           PaymentRepository paymentRepository,
                           SubjectLogRepository subjectLogRepository,
                           JdbcTemplate jdbcTemplate,
                           FinancialService financialService) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
        this.subjectLogRepository = subjectLogRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.financialService = financialService;
    }

    @GetMapping("/dashboard")
    public String dashboard(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        long totalEnrollees = studentRepository.count();
        long pendingCount = studentRepository.findAll().stream()
                .filter(s -> "PENDING".equalsIgnoreCase(s.getApplicantStatus()))
                .count();

        List<Student> searchResults;
        if (keyword != null && !keyword.trim().isEmpty()) {
            searchResults = studentRepository.findByStudentNumberContainingOrLastNameContainingIgnoreCase(keyword, keyword);
        } else {
            searchResults = studentRepository.findAll(); 
        }

        model.addAttribute("totalEnrollees", totalEnrollees);
        model.addAttribute("pendingCount", pendingCount);
        model.addAttribute("students", searchResults);
        model.addAttribute("keyword", keyword);

        return "AdminDashboard"; 
    }
    
    @GetMapping("/walkin-payment")
    public String showWalkinPage(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            // 1. Search for the student (Same logic as Ledger)
            Student s = studentRepository.findByStudentNumber(keyword.trim());
            if (s == null) {
                List<Student> results = studentRepository.findByLastNameContainingIgnoreCase(keyword.trim());
                if (!results.isEmpty()) s = results.get(0);
            }

            if (s != null) {
                // 2. Sync the ledger data
                model.addAttribute("student", s);
                financialService.populateStudentFinancialData(s, model); // This fills outstandingBalance, installments, etc.
                model.addAttribute("keyword", keyword);
            } else {
                model.addAttribute("errorMessage", "No student found with that ID or Name.");
            }
        }
        return "admin_walkin_payment"; // Your HTML file name
    }

    private void calculateFinancials(Student s, Model model) {
		// TODO Auto-generated method stub
    	financialService.populateStudentFinancialData(s, model);
	}

    @PostMapping("/process-walkin")
    public String processWalkInPayment(
            @RequestParam("studentIdentifier") String studentIdentifier,
            @RequestParam("amount") Double amount,
            @RequestParam(value = "paymentType", defaultValue = "Cash") String paymentType,
            @RequestParam(value = "remarks", required = false) String remarks, // Added remarks parameter
            RedirectAttributes redirectAttributes 
    ) {
        String trimmedId = studentIdentifier.trim();
        
        // Search by Student Number first
        Student student = studentRepository.findByStudentNumber(trimmedId);
        
        // If not found by Number, search by Last Name
        if (student == null) {
            List<Student> results = studentRepository.findByLastNameContainingIgnoreCase(trimmedId);
            if (!results.isEmpty()) {
                student = results.get(0); // Take the first match
            }
        }

        // If still null, return error
        if (student == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error: Student not found with ID or Surname: " + trimmedId);
            return "redirect:/admin/walkin-payment";
        }

        Payment payment = new Payment();
        String transactionId = "WLK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase(); 
        payment.setTransactionId(transactionId);
        
        // Link to student
        payment.setReferenceNumber(student.getStudentNumber()); 
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentType + " (Over the Counter)"); 
        
        // ➤ SAVE THE REMARKS
        payment.setRemarks(remarks); 
        
        payment.setPaymentDate(new Date());
        payment.setStatus("COMPLETED");
        
        paymentRepository.save(payment);
        
        // Update student status if needed
        Double tuitionPayments = jdbcTemplate.queryForObject(
                "SELECT SUM(amount) FROM payments WHERE reference_number = ? " +
                "AND (remarks = 'Tuition Fee' OR remarks IS NULL OR remarks = '')", 
                Double.class, student.getStudentNumber());
                
            double totalPaid = (tuitionPayments != null) ? tuitionPayments : 0.00;

            // 2. Change status based on the 3000 Downpayment Threshold
            if (totalPaid >= 3000.00) {
                // If they paid 3000 or more, they are ENROLLED
                if (!"ENROLLED".equalsIgnoreCase(student.getApplicantStatus())) {
                    student.setApplicantStatus("ENROLLED");
                    studentRepository.save(student);
                }
            } else {
                // If they haven't hit 3000 yet, they stay PENDING
                if (!"PENDING".equalsIgnoreCase(student.getApplicantStatus())) {
                    student.setApplicantStatus("PENDING");
                    studentRepository.save(student);
                }
            }

        redirectAttributes.addFlashAttribute("successMessage", "Payment successful for " + student.getLastName() + " (" + student.getStudentNumber() + ")");
        redirectAttributes.addFlashAttribute("transactionId", transactionId);
        
        // Redirect back with keyword to keep the student's info displayed
        return "redirect:/admin/walkin-payment?keyword=" + student.getStudentNumber();
    }
    
    @GetMapping("/history-subject")
    public String showSubjectHistory(Model model) {
        List<SubjectLog> logs = subjectLogRepository.findAllByOrderByTimestampDesc();
        model.addAttribute("logs", logs);
        return "admin_history_subject";
    }
}
