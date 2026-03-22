package com.example.COLLEGE_MANAGEMENT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.COLLEGE_MANAGEMENT.entities.ProfessorEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
}
