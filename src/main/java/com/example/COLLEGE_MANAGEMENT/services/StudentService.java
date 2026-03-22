package com.example.COLLEGE_MANAGEMENT.services;

import com.example.COLLEGE_MANAGEMENT.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    StudentDTO createStudent(StudentDTO studentDTO);

    StudentDTO getStudentById(Long id);

    List<StudentDTO> getAllStudents();

    void deleteStudent(Long id);

    StudentDTO assignSubjects(Long studentId, List<Long> subjectIds);

    StudentDTO assignProfessors(Long studentId, List<Long> professorIds);
}