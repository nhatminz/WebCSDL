package com.example.webcsdl.Repository;

import com.example.webcsdl.Entity.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {
    List<Scholarship> findByScholarshipNameContainingIgnoreCase(String name);
}