package com.example.COLLEGE_MANAGEMENT.controllers;

import com.example.COLLEGE_MANAGEMENT.dto.SubjectDTO;
import com.example.COLLEGE_MANAGEMENT.services.SubjectService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    // ✅ Create Subject
    @PostMapping
    public SubjectDTO createSubject(@Valid @RequestBody SubjectDTO subjectDTO) {
        return subjectService.createSubject(subjectDTO);
    }

    // ✅ Get Subject by ID
    @GetMapping("/{id}")
    public SubjectDTO getSubjectById(@PathVariable Long id) {
        return subjectService.getSubjectById(id);
    }

    // ✅ Get All Subjects
    @GetMapping
    public List<SubjectDTO> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    // ✅ Delete Subject
    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
    }

    // ✅ Assign Professor (Many-to-One)
    @PutMapping("/{id}/professor")
    public SubjectDTO assignProfessor(
            @PathVariable Long id,
            @RequestParam Long professorId
    ) {
        return subjectService.assignProfessor(id, professorId);
    }

    // ✅ Assign Students (Many-to-Many)
    @PutMapping("/{id}/students")
    public SubjectDTO assignStudents(
            @PathVariable Long id,
            @RequestBody List<Long> studentIds
    ) {
        return subjectService.assignStudents(id, studentIds);
    }
}