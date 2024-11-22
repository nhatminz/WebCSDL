package com.example.webcsdl.Entity;

import java.io.Serializable;
import java.util.Objects;

public class EnrollmentId implements Serializable {

    private Integer studentId;
    private Integer courseId;

    // Constructor mặc định
    public EnrollmentId() {}

    public EnrollmentId(Integer studentId, Integer courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    // Getter và Setter cho studentId
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    // Getter và Setter cho courseId
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    // equals và hashCode cần thiết cho composite key
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentId that = (EnrollmentId) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
