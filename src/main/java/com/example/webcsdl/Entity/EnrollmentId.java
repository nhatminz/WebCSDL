package com.example.webcsdl.Entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
@Setter
@Getter
@Embeddable
public class EnrollmentId implements Serializable {

    // Getter và Setter cho studentId
    private Long studentId;
    // Getter và Setter cho courseId
    private Long courseId;

    // Constructor mặc định
    public EnrollmentId() {}

    public EnrollmentId(Long studentId, Long courseId) {
        this.studentId = studentId;
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
