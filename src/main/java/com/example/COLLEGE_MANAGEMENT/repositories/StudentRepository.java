package com.example.COLLEGE_MANAGEMENT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.COLLEGE_MANAGEMENT.entities.StudentEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
