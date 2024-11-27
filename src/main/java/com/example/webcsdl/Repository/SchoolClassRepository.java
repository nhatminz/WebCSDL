package com.example.webcsdl.Repository;

import com.example.webcsdl.Entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
    Optional<SchoolClass> findByClassName(String className);
}