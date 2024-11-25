package com.example.webcsdl.Repository;

import com.example.webcsdl.Entity.Course;
import com.example.webcsdl.Entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}