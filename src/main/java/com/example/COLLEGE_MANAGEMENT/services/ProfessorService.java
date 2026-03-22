package com.example.COLLEGE_MANAGEMENT.services;

import com.example.COLLEGE_MANAGEMENT.dto.ProfessorDTO;

import java.util.List;

public interface ProfessorService {

    ProfessorDTO createProfessor(ProfessorDTO professorDTO);

    ProfessorDTO getProfessorById(Long id);

    List<ProfessorDTO> getAllProfessors();

    void deleteProfessor(Long id);

    ProfessorDTO assignSubjects(Long professorId, List<Long> subjectIds);

    ProfessorDTO assignStudents(Long professorId, List<Long> studentIds);
}