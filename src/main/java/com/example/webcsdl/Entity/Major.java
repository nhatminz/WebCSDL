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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "major", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "major", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Course> courses = new ArrayList<>();


    // Helper methods
    public void addCourse(Course course) {
        courses.add(course);
        course.setMajor(this);
    }

    public void addStudent(Student student) {
        students.add(student);
        student.setMajor(this);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.setMajor(null);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        course.setMajor(null);
    }

}
