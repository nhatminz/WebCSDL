package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.Enrollment;
import com.example.webcsdl.Entity.EnrollmentId;
import com.example.webcsdl.Repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Enrollment getById(Long studentId, Long courseId) {
        EnrollmentId enrollmentId = new EnrollmentId(studentId, courseId);
        Optional<Enrollment> enrollment = enrollmentRepository.findById(enrollmentId);
        Enrollment enroll = null;

        if (enrollment.isPresent()) {
            enroll = enrollment.get();
        } else {
            throw new RuntimeException("Enrollment not found");
        }
        return enroll;
    }

    @Override
    public List<Enrollment> searchEnrollments(String keyword) {
        // Kiểm tra nếu từ khóa có chứa dấu cách (nghĩa là tìm kiếm tên đầy đủ)
        if (keyword.contains(" ")) {
            return enrollmentRepository.findAll().stream()
                    .filter(e -> {
                        String fullName = (e.getStudent().getFirstName() + " " + e.getStudent().getLastName()).toLowerCase();
                        return fullName.equals(keyword.toLowerCase()) ||
                                fullName.contains(keyword.toLowerCase()) ||
                                e.getCourse().getCourseName().toLowerCase().contains(keyword.toLowerCase()) ||
                                String.valueOf(e.getId().getStudentId()).contains(keyword) ||
                                String.valueOf(e.getId().getCourseId()).contains(keyword);
                    })
                    .toList();
        } else {
            // Nếu không có dấu cách, giữ nguyên logic tìm kiếm cũ
            return enrollmentRepository.findAll().stream()
                    .filter(e -> {
                        return e.getStudent().getFirstName().toLowerCase().contains(keyword.toLowerCase()) ||
                                e.getStudent().getLastName().toLowerCase().contains(keyword.toLowerCase()) ||
                                e.getCourse().getCourseName().toLowerCase().contains(keyword.toLowerCase()) ||
                                String.valueOf(e.getId().getStudentId()).contains(keyword) ||
                                String.valueOf(e.getId().getCourseId()).contains(keyword);
                    })
                    .toList();
        }
    }



    @Override
    public void deleteViaId(EnrollmentId enrollmentId) {
    enrollmentRepository.deleteById(enrollmentId);
    }
}
