package com.example.enrollment.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionId;
    private String referenceNumber; // Links payment to the student
    private Double amount;
    private String paymentMethod;
    private Date paymentDate;
    private String remarks;

    // --- GETTERS AND SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getReferenceNumber() { return referenceNumber; }
    public void setReferenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }
	public void setPaymentDate(LocalDateTime now) {
		// TODO Auto-generated method stub
		
	}
	public void setStatus(String string) {
		// TODO Auto-generated method stub
		
	}
	public String getStudentNumber() {	
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}