package com.example.webcsdl.Repository;

import com.example.webcsdl.Entity.Enrollment;
import com.example.webcsdl.Entity.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {
    @Query("SELECT e FROM Enrollment e WHERE " +
            "LOWER(CONCAT(e.student.firstName, ' ', e.student.lastName)) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(e.course.courseName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "STR(e.id.studentId) LIKE CONCAT('%', :keyword, '%') OR " +
            "STR(e.id.courseId) LIKE CONCAT('%', :keyword, '%')")
    List<Enrollment> searchEnrollments(@Param("keyword") String keyword);
}
