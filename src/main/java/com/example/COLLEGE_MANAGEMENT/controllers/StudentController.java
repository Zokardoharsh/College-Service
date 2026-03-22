package com.example.COLLEGE_MANAGEMENT.controllers;

import com.example.COLLEGE_MANAGEMENT.dto.StudentDTO;
import com.example.COLLEGE_MANAGEMENT.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // ✅ Create Student
    @PostMapping
    public StudentDTO createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }

    // ✅ Get Student by ID
    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // ✅ Get All Students
    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    // ✅ Delete Student
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    // ✅ Assign Subjects
    @PutMapping("/{id}/subjects")
    public StudentDTO assignSubjects(
            @PathVariable Long id,
            @RequestBody List<Long> subjectIds
    ) {
        return studentService.assignSubjects(id, subjectIds);
    }

    // ✅ Assign Professors
    @PutMapping("/{id}/professors")
    public StudentDTO assignProfessors(
            @PathVariable Long id,
            @RequestBody List<Long> professorIds
    ) {
        return studentService.assignProfessors(id, professorIds);
    }
}