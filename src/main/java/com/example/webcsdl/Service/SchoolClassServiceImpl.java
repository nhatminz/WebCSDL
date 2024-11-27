package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.SchoolClass;
import com.example.webcsdl.Repository.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolClassServiceImpl implements SchoolClassServices {
    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Override
    public List<SchoolClass> getAllSchoolClass() {
        return schoolClassRepository.findAll();
    }

    @Override
    public void saveSchoolClass(SchoolClass schoolClass) {
        schoolClassRepository.save(schoolClass);
    }

    @Override
    public SchoolClass getById(Long id) {
        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(id);
        return schoolClass.orElseThrow(() -> new RuntimeException("School class not found"));
    }

    @Override
    public void deleteViaId(Long id) {
        schoolClassRepository.deleteById(id);
    }

    @Override
    public Optional<SchoolClass> getByName(String schoolName) {
        return schoolClassRepository.findByClassName(schoolName);
    }

    public void deleteSchoolClassById(Long id) {
        schoolClassRepository.deleteById(id);
    }

    public SchoolClass getSchoolClassById(Long id) {
        return schoolClassRepository.findById(id).orElse(null);
    }
}
