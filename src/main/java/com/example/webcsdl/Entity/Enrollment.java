package com.example.webcsdl.Entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @EmbeddedId
    private EnrollmentId id; // Composite key (EmbeddedId)

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")  // Ánh xạ với studentId trong EnrollmentId
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId")   // Ánh xạ với courseId trong EnrollmentId
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    @Column(name = "enrollment_date")
    private Date enrollmentDate;

    // Constructor mặc định
    public Enrollment() {}

    public Enrollment(EnrollmentId id, Student student, Course course, Date enrollmentDate) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
    }

    // Getter và Setter cho id
    public EnrollmentId getId() {
        return id;
    }

    public void setId(EnrollmentId id) {
        this.id = id;
    }

    // Getter và Setter cho student
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    // Getter và Setter cho course
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    // Getter và Setter cho enrollmentDate
    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
