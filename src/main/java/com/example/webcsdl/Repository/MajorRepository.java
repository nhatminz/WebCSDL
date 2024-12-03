package com.example.webcsdl.Repository;

import com.example.webcsdl.Entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MajorRepository extends JpaRepository<Major, Long> {

    @Query("SELECT m FROM Major m WHERE " +
            "LOWER(m.majorName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(m.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(m.department.departmentName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Major> searchMajors(@Param("keyword") String keyword);
}