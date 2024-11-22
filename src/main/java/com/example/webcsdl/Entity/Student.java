package com.example.webcsdl.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "students")
public class Student {
    // Getter and Setter methods
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "address", length = 255)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private SchoolClass studentClass;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "major_id", referencedColumnName = "id")
    private Major major;

    @Column(name = "gpa", precision = 3, scale = 2)
    private BigDecimal gpa;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Scholarship> scholarships = new ArrayList<>();

}
