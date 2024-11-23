package com.example.webcsdl.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classes")
public class SchoolClass {
    @Setter
    @Getter
    @Id
    private Long id;

    @Setter
    @Getter
    @Column(name = "class_name", nullable = false, length = 50)
    private String className;

    @Setter
    @Getter
    @Column(name = "class_description", length = 255)
    private String classDescription;

    @OneToMany(mappedBy = "studentClass", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Student> students = new ArrayList<>();

    // Hepler methods
    public void addStudent(Student student) {
        students.add(student);
        student.setStudentClass(this);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.setStudentClass(null);
    }

}
