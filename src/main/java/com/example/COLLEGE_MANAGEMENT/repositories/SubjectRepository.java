package com.example.COLLEGE_MANAGEMENT.repositories;

import com.example.COLLEGE_MANAGEMENT.entities.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
}
