package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.SchoolClass;
import com.example.webcsdl.Entity.Student;
import com.example.webcsdl.Repository.SchoolClassRepository;
import com.example.webcsdl.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolClassServiceImpl implements SchoolClassServices {
    @Autowired
    SchoolClassRepository schoolClassRepository;

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
        SchoolClass schClass = null;
        if (schoolClass.isPresent()) {
            schClass = schoolClass.get();
        } else {
            throw new RuntimeException("School class not found");
        }
        return schClass;
    }

    @Override
    public void deleteViaId(Long id) {
        schoolClassRepository.deleteById(id);
    }
    @Override
    public Optional<SchoolClass> getByName(String schoolName) {
        for (SchoolClass schoolClass : getAllSchoolClass()) {
            if (schoolClass.getClassName().equals(schoolName)) {
                return Optional.of(schoolClass);
            }
        }

        return Optional.empty();
    }

    public List<SchoolClass> searchClasses(String query) {
        return schoolClassRepository.findByClassNameContainingOrClassDescriptionContaining(query, query);
    }
}