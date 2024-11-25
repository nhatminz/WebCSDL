package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.Major;


import java.util.List;

public interface MajorServices {
    List<Major> getAllMajor();
    void saveMajor(Major major);
    Major getById(Long id);
    void deleteViaId(Long id);
}
