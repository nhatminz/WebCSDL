package com.example.webcsdl.Repository;

import com.example.webcsdl.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    long count();

    @Query("SELECT s FROM Student s WHERE " +
            "CAST(s.id AS string) LIKE CONCAT('%', :keyword, '%') OR " +
            "LOWER(s.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(s.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(CONCAT(s.firstName, ' ', s.lastName)) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(s.studentClass.className) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(s.major.majorName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Student> searchStudents(@Param("keyword") String keyword);
}