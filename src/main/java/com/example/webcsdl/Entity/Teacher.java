package com.example.webcsdl.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {
    // Getter and Setter methods
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Setter
    @Getter
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Setter
    @Getter
    @Column(name = "email", length = 100)
    private String email;

    @Setter
    @Getter
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses = new ArrayList<>();

}
