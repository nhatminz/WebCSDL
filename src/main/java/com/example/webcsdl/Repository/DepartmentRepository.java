package com.example.webcsdl.Repository;

import com.example.webcsdl.Entity.Department;
import com.example.webcsdl.Entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByDepartmentNameContaining(String departmentName);
}
