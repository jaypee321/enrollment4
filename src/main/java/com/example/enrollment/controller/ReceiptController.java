package com.example.enrollment.controller;

import com.example.enrollment.entity.Payment;
import com.example.enrollment.entity.Student;
import com.example.enrollment.repository.PaymentRepository;
import com.example.enrollment.repository.StudentRepository;
import com.example.enrollment.util.NumberToWords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 1. CHANGED IMPORT: Use SimpleDateFormat for java.util.Date types
import java.text.SimpleDateFormat;

@Controller
public class ReceiptController {

    @Autowired private PaymentRepository paymentRepository;
    @Autowired private StudentRepository studentRepository;

    @GetMapping("/print-receipt/{transactionId}")
    public String printReceipt(@PathVariable String transactionId, Model model) {
        // Find payment by Transaction ID
        Payment payment = paymentRepository.findByTransactionId(transactionId);
        
        if (payment == null) {
            return "redirect:/account-status"; // Redirect back if not found
        }
        
        // Find the student using the reference number stored in the payment
        // (This matches the HTML form where name="referenceNumber" holds the student ID)
        Student student = studentRepository.findByStudentNumber(payment.getReferenceNumber());

        model.addAttribute("payment", payment);
        model.addAttribute("student", student);
        
        // Convert Amount to Words
        model.addAttribute("amountInWords", NumberToWords.convertAmount(payment.getAmount()));
        
        // 2. THE FIX: formatting the Date using SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String dateString = sdf.format(payment.getPaymentDate());
        model.addAttribute("formattedDate", dateString);
        
        // Set Term (You can make this dynamic later)
        model.addAttribute("term", "1st Sem, A.Y. 2024-2025"); 
        
        return "receipt";
    }
}