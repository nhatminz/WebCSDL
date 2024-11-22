package com.example.webcsdl.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "majors")
public class Major {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(name = "major_name", nullable = false, length = 100)
    private String majorName;

    @Setter
    @Getter
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @OneToMany(mappedBy = "major", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "major", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses = new ArrayList<>();


    // Getter and Setter methods

}
