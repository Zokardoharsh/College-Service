package com.example.COLLEGE_MANAGEMENT.controllers;

import com.example.COLLEGE_MANAGEMENT.dto.ProfessorDTO;
import com.example.COLLEGE_MANAGEMENT.services.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    // ✅ Create Professor
    @PostMapping
    public ProfessorDTO createProfessor(@Valid @RequestBody ProfessorDTO professorDTO) {
        return professorService.createProfessor(professorDTO);
    }

    // ✅ Get by ID
    @GetMapping("/{id}")
    public ProfessorDTO getProfessorById(@PathVariable Long id) {
        return professorService.getProfessorById(id);
    }

    // ✅ Get All
    @GetMapping
    public List<ProfessorDTO> getAllProfessors() {
        return professorService.getAllProfessors();
    }

    // ✅ Delete
    @DeleteMapping("/{id}")
    public void deleteProfessor(@PathVariable Long id) {
        professorService.deleteProfessor(id);
    }

    // ✅ Assign Subjects (One-to-Many)
    @PutMapping("/{id}/subjects")
    public ProfessorDTO assignSubjects(
            @PathVariable Long id,
            @RequestBody List<Long> subjectIds
    ) {
        return professorService.assignSubjects(id, subjectIds);
    }

    // ✅ Assign Students (Many-to-Many)
    @PutMapping("/{id}/students")
    public ProfessorDTO assignStudents(
            @PathVariable Long id,
            @RequestBody List<Long> studentIds
    ) {
        return professorService.assignStudents(id, studentIds);
    }
}