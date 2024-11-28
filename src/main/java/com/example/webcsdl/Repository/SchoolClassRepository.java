package com.example.webcsdl.Repository;

import com.example.webcsdl.Entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
    List<SchoolClass> findByClassNameContainingOrClassDescriptionContaining(String className, String classDescription);
    Optional<SchoolClass> findByClassName(String className);
}