package com.example.webcsdl.Repository;

import com.example.webcsdl.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    long count();
    @Query("SELECT c FROM Course c " +
            "JOIN c.major m " +
            "JOIN c.teacher t " +
            "WHERE " +
            "(LOWER(c.courseName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.courseCode) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "c.credits = :credits OR " +
            "LOWER(m.majorName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(CONCAT(t.firstName, ' ', t.lastName)) LIKE LOWER(CONCAT('%', :keyword, '%'))) ")
    List<Course> searchCourses(@Param("keyword") String keyword, @Param("credits") Integer credits);
}