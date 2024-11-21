package com.example.webcsdl.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "scholarships")
public class Scholarships {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AI (Auto Increment)
    private Integer id;

    @Column(name = "scholarship_name", length = 100, nullable = false)
    private String scholarshipName;

    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    // Getters và Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScholarshipName() {
        return scholarshipName;
    }

    public void setScholarshipName(String scholarshipName) {
        this.scholarshipName = scholarshipName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
