package com.example.enrollment.repository;

import com.example.enrollment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    // Standard find method
    List<Payment> findByReferenceNumber(String referenceNumber);

    // ➤ NEW: Calculate Total Payments
    // COALESCE ensures that if no payments exist, it returns 0 instead of null
    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.referenceNumber = :referenceNumber")
    Double getTotalPaymentsByReferenceNumber(@Param("referenceNumber") String referenceNumber);

	Payment findByTransactionId(String transactionId);

    
}