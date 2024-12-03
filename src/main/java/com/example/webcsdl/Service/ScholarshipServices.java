package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.Scholarship;

import java.util.List;

public interface ScholarshipServices {
    List<Scholarship> getAllScholarship();
    void saveScholarship(Scholarship scholarship);
    Scholarship getById(Long id);
    void deleteViaId(Long id);
    List<Scholarship> searchScholarships(String keyword);
}
