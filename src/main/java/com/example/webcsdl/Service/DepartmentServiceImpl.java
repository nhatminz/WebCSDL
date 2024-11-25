package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.Department;
import com.example.webcsdl.Entity.Major;
import com.example.webcsdl.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DepartmentServiceImpl implements DepartmentServices {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public void saveDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public Department getById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        Department depart = null;
        if (department.isPresent()) {
            depart = department.get();
        } else {
            throw new RuntimeException("Department not found");
        }
        return depart;
    }

    @Override
    public void deleteViaId(Long id) {
        departmentRepository.deleteById(id);
    }
}
