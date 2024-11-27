package com.example.webcsdl.Repository;

import com.example.webcsdl.Entity.Classroom;
import com.example.webcsdl.Entity.Course;
import com.example.webcsdl.Entity.CourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseScheduleRepository extends JpaRepository<CourseSchedule,Long> {
}
