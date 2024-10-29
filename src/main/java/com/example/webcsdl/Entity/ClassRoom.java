package com.example.webcsdl.Entity;

import java.util.ArrayList;
import java.util.List;

public class ClassRoom {
    private String name;
    private String course;
    private List<Student> students;
    private String advisor;

    public ClassRoom(String name, String course, String advisor) {
        this.name = name;
        this.course = course;
        this.advisor = advisor;
        this.students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public List<Student> getStudents() {
        return students;
    }

    public String getAdvisor() {
        return advisor;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(String studentId) {
        students.removeIf(student -> student.getId().equals(studentId));
    }
}
