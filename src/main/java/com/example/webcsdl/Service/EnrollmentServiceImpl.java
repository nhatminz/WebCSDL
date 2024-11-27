package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.Enrollment;
import com.example.webcsdl.Repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements EnrollmentServices {
   @Autowired
   private EnrollmentRepository enrollmentRepository;
    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public void saveEnrollment(Enrollment enrollment) {
    enrollmentRepository.save(enrollment);
    }

    @Override
    public Enrollment getById(Long id) {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(id);
        Enrollment enroll = null;

        if (enrollment.isPresent()) {
            enroll = enrollment.get();
        } else {
            throw new RuntimeException("Enrollment not found");
        }
        return enroll;
    }

    @Override
    public void deleteViaId(Long id) {
    enrollmentRepository.deleteById(id);
    }
}
