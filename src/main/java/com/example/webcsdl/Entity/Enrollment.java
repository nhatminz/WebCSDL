package com.example.webcsdl.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "enrollments")
public class Enrollment {

    // Getter và Setter cho id
    @EmbeddedId
    private EnrollmentId id; // Composite key (EmbeddedId)

    // Getter và Setter cho student
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("studentId")  // Ánh xạ với studentId trong EnrollmentId
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    // Getter và Setter cho course
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("courseId")   // Ánh xạ với courseId trong EnrollmentId
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    // Getter và Setter cho enrollmentDate
    @Column(name = "enrollment_date")
    private Date enrollmentDate;

    @Column(name = "grade")
    private Double grade;

    // Constructor mặc định
    public Enrollment() {}

    public Enrollment(EnrollmentId id, Student student, Course course, Date enrollmentDate) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
    }

}
