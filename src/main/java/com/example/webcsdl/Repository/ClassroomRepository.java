package com.example.webcsdl.Repository;

import com.example.webcsdl.Entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom,Long> {
}
