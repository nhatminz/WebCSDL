package com.example.webcsdl.Repository;

import com.example.webcsdl.Entity.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {
    @Query("SELECT s FROM Scholarship s WHERE " +
            "LOWER(s.scholarshipName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(CONCAT(s.student.firstName, ' ', s.student.lastName)) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(s.amount AS string) LIKE CONCAT('%', :keyword, '%')")
    List<Scholarship> searchScholarships(@Param("keyword") String keyword);
}