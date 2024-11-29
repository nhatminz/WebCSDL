package com.example.webcsdl.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {
    @Setter
    @Getter
    @Id
    private Long id;

    @Setter
    @Getter
    @Column(name = "department_name", nullable = false, length = 100)
    private String departmentName;

    @Setter
    @Getter
    @Column(name = "location", length = 100)
    private String location;

    @OneToMany(mappedBy = "department", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Major> majors = new ArrayList<>();

    @OneToMany(mappedBy = "department", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Teacher> teachers = new ArrayList<>();

    // Helper Methods
    public void addMajor(Major major) {
        majors.add(major);
        major.setDepartment(this);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        teacher.setDepartment(this);
    }

    public void removeMajor(Major major) {
        majors.remove(major);
        major.setDepartment(null);
    }

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
        teacher.setDepartment(null);
    }

}
