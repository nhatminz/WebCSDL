package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.Enrollment;

import java.util.List;

public interface EnrollmentServices {
    List<Enrollment> getAllEnrollments();
    void saveEnrollment(Enrollment enrollment);
    Enrollment getById(Long id);
    void deleteViaId(Long id);
}
