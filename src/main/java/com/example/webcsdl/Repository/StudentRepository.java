package com.example.webcsdl.Repository;

import com.example.webcsdl.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
