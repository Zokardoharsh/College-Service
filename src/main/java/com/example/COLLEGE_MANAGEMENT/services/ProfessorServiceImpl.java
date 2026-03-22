package com.example.COLLEGE_MANAGEMENT.services;

import com.example.COLLEGE_MANAGEMENT.dto.ProfessorDTO;
import com.example.COLLEGE_MANAGEMENT.entities.*;
import com.example.COLLEGE_MANAGEMENT.exceptions.ResourceNotFoundException;
import com.example.COLLEGE_MANAGEMENT.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public ProfessorServiceImpl(ProfessorRepository professorRepository,
                                SubjectRepository subjectRepository,
                                StudentRepository studentRepository,
                                ModelMapper modelMapper) {
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    // ✅ Create
    @Override
    public ProfessorDTO createProfessor(ProfessorDTO dto) {
        ProfessorEntity professor = modelMapper.map(dto, ProfessorEntity.class);
        ProfessorEntity saved = professorRepository.save(professor);
        return convertToDTO(saved);
    }

    // ✅ Get by ID
    @Override
    public ProfessorDTO getProfessorById(Long id) {
        ProfessorEntity professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found with id: " + id));
        return convertToDTO(professor);
    }

    // ✅ Get All
    @Override
    public List<ProfessorDTO> getAllProfessors() {
        return professorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Delete
    @Override
    public void deleteProfessor(Long id) {
        if (!professorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Professor not found with id: " + id);
        }
        professorRepository.deleteById(id);
    }

    // ✅ Assign Subjects (One-to-Many side)
    @Override
    public ProfessorDTO assignSubjects(Long professorId, List<Long> subjectIds) {

        ProfessorEntity professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found"));

        List<SubjectEntity> subjects = subjectRepository.findAllById(subjectIds);

        // Important: set professor inside each subject
        for (SubjectEntity subject : subjects) {
            subject.setProfessor(professor);
        }

        subjectRepository.saveAll(subjects);

        return convertToDTO(professorRepository.findById(professorId).get());
    }

    // ✅ Assign Students (Many-to-Many)
    @Override
    public ProfessorDTO assignStudents(Long professorId, List<Long> studentIds) {

        ProfessorEntity professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found"));

        List<StudentEntity> students = studentRepository.findAllById(studentIds);

        professor.setStudents(students);

        return convertToDTO(professorRepository.save(professor));
    }

    // 🔄 Entity → DTO
    private ProfessorDTO convertToDTO(ProfessorEntity entity) {
        ProfessorDTO dto = modelMapper.map(entity, ProfessorDTO.class);

        if (entity.getSubjects() != null) {
            dto.setSubjectIds(
                    entity.getSubjects().stream().map(SubjectEntity::getId).toList()
            );
        }

        if (entity.getStudents() != null) {
            dto.setStudentIds(
                    entity.getStudents().stream().map(StudentEntity::getId).toList()
            );
        }

        return dto;
    }
}