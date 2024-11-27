package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.Classroom;
import com.example.webcsdl.Repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClassroomServiceImpl implements ClassroomServices {
    @Autowired
    private ClassroomRepository classroomRepository;

    @Override
    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    @Override
    public void saveClassroom(Classroom classroom) {
        classroomRepository.save(classroom);
    }

    @Override
    public Classroom getById(Long id) {
        Optional<Classroom> classroom = classroomRepository.findById(id);
        Classroom classroom1 = null;
        if (classroom.isPresent()) {
            classroom1 = classroom.get();
        } else {
            throw new RuntimeException("Classroom not found");
        }
        return classroom1;
    }

    @Override
    public void deleteViaId(Long id) {
        classroomRepository.deleteById(id);
    }
}
