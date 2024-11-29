package com.example.webcsdl.Repository;

import com.example.webcsdl.Entity.CourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseScheduleRepository extends JpaRepository<CourseSchedule, Long> {
    List<CourseSchedule> findByClassroomId(Long classroom_id);
}
