package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.Major;
import com.example.webcsdl.Repository.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MajorServiceImpl implements MajorServices {
    @Autowired
    private MajorRepository majorRepository;

    @Override
    public List<Major> getAllMajor() {
        return majorRepository.findAll();
    }

    @Override
    public void saveMajor(Major major) {
        majorRepository.save(major);
    }

    @Override
    public Major getById(Long id) {
        Optional<Major> major = majorRepository.findById(id);
        Major maj = null;
        if (major.isPresent()) {
            maj = major.get();
        } else {
            throw new RuntimeException("Major not found");
        }
        return maj;
    }

    @Override
    public void deleteViaId(Long id) {
        majorRepository.deleteById(id);
    }

    @Override
    public List<Major> searchMajors(String keyword) {
        return majorRepository.searchMajors(keyword);
    }

    @Override
    public Optional<Major> getMajorByName(String name) {
        for (Major major : getAllMajor()) {
            if (major.getMajorName().equals(name)) {
                return Optional.of(major);
            }
        }
        return Optional.empty();
    }
}