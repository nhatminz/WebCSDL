package com.example.webcsdl.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name", nullable = false, length = 100)
    private String courseName;

    @Column(name = "course_code", nullable = false, length = 20)
    private String courseCode;

    @Column(name = "credits")
    private Integer credits;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "major_id", referencedColumnName = "id")
    private Major major;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseSchedule> schedules = new ArrayList<>();

    // Getters and Setters

}
