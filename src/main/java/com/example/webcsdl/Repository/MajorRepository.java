package com.example.webcsdl.Repository;

import com.example.webcsdl.Entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MajorRepository extends JpaRepository<Major, Long> {
    List<Major> findByMajorNameContainingIgnoreCase(String majorName);
}