package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.Enrollment;
import com.example.webcsdl.Entity.EnrollmentId;

import java.util.List;

public interface EnrollmentServices {
    List<Enrollment> getAllEnrollments();
    void saveEnrollment(Enrollment enrollment);
    Enrollment getById(Long studentId, Long courseId);
    List<Enrollment> searchEnrollments(String keyword);
    void deleteViaId(EnrollmentId enrollmentId);
}
