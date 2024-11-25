package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.SchoolClass;

import java.util.List;

public interface SchoolClassServices {
    List<SchoolClass> getAllSchoolClass();
    void saveSchoolClass(SchoolClass schoolClass);
    SchoolClass getById(Long id);
    void deleteViaId(Long id);
}