package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.Scholarship;
import com.example.webcsdl.Repository.ScholarshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScholarshipServiceImpl implements ScholarshipServices {

    @Autowired
    private ScholarshipRepository scholarshipRepository;

    @Override
    public List<Scholarship> getAllScholarship() {
        return scholarshipRepository.findAll();
    }

    @Override
    public void saveScholarship(Scholarship scholarship) {
        scholarshipRepository.save(scholarship);
    }

    @Override
    public Scholarship getById(Long id) {
        Optional<Scholarship> scholarship = scholarshipRepository.findById(id);
        Scholarship scho = null;

        if (scholarship.isPresent()) {
            scho = scholarship.get();
        } else {
            throw new RuntimeException("Scholarship not found");
        }
        return scho;
    }

    @Override
    public void deleteViaId(Long id) {
        scholarshipRepository.deleteById(id);
    }

    @Override
    public List<Scholarship> searchScholarships(String keyword) {
        return scholarshipRepository.searchScholarships(keyword);
    }

}
