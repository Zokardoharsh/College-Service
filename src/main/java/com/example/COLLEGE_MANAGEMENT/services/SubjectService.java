package com.example.COLLEGE_MANAGEMENT.services;

import com.example.COLLEGE_MANAGEMENT.dto.SubjectDTO;

import java.util.List;

public interface SubjectService {

    SubjectDTO createSubject(SubjectDTO subjectDTO);

    SubjectDTO getSubjectById(Long id);

    List<SubjectDTO> getAllSubjects();

    void deleteSubject(Long id);

    SubjectDTO assignProfessor(Long subjectId, Long professorId);

    SubjectDTO assignStudents(Long subjectId, List<Long> studentIds);
}