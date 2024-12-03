package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.Major;


import java.util.List;
import java.util.Optional;

public interface MajorServices {
    List<Major> getAllMajor();
    void saveMajor(Major major);
    Major getById(Long id);
    void deleteViaId(Long id);
    Optional<Major> getMajorByName(String name);
    List<Major> searchMajors(String keyword);
}