package com.example.webcsdl.Repository;

import com.example.webcsdl.Entity.CourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseScheduleRepository extends JpaRepository<CourseSchedule, Long> {
    @Query("SELECT cs FROM CourseSchedule cs " +
            "JOIN cs.course c " +
            "JOIN cs.classroom cl " +
            "WHERE " +
            "LOWER(c.courseName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(cl.id AS string) LIKE CONCAT('%', :keyword, '%') OR " +
            "LOWER(cs.dayOfWeek) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<CourseSchedule> searchCourseSchedules(@Param("keyword") String keyword);

}
