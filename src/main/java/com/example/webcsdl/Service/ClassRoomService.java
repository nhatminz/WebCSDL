package com.example.webcsdl.Service;

import com.example.webcsdl.Entity.ClassRoom;
import com.example.webcsdl.Entity.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassRoomService {
    private final List<ClassRoom> classRooms;

    public ClassRoomService() {
        classRooms = new ArrayList<>();
        ClassRoom classA = new ClassRoom("Lớp A", "Khóa học 2024", "Giáo viên A");
        classA.addStudent(new Student("Sinh viên 1", "SV001"));
        classA.addStudent(new Student("Sinh viên 2", "SV002"));

        ClassRoom classB = new ClassRoom("Lớp B", "Khóa học 2024", "Giáo viên B");
        classB.addStudent(new Student("Sinh viên 3", "SV003"));

        classRooms.add(classA);
        classRooms.add(classB);
    }

    public List<ClassRoom> findAll() {
        return classRooms;
    }

    public Optional<ClassRoom> findByName(String name) {
        return classRooms.stream()
                .filter(classRoom -> classRoom.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public void addStudentToClass(String className, Student student) {
        findByName(className).ifPresent(classRoom -> classRoom.addStudent(student));
    }

    public void removeStudentFromClass(String className, String studentId) {
        findByName(className).ifPresent(classRoom -> classRoom.removeStudent(studentId));
    }
}