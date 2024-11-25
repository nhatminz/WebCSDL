package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.Department;
import com.example.webcsdl.Entity.Major;

import java.util.List;

public interface DepartmentServices {
    List<Department> getAllDepartment();
    void saveDepartment(Department department);
    Department getById(Long id);
    void deleteViaId(Long id);
}
