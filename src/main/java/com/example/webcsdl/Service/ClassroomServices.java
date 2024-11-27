package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.Classroom;

import java.util.List;

public interface ClassroomServices {
    List<Classroom> getAllClassrooms();
    void saveClassroom(Classroom classroom);
    Classroom getById(Long id);
    void deleteViaId(Long id);
}
