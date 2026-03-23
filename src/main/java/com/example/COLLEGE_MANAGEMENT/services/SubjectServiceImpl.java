package com.example.COLLEGE_MANAGEMENT.services.impl;

import com.example.COLLEGE_MANAGEMENT.dto.SubjectDTO;
import com.example.COLLEGE_MANAGEMENT.entities.*;
import com.example.COLLEGE_MANAGEMENT.exceptions.ResourceNotFoundException;
import com.example.COLLEGE_MANAGEMENT.repositories.*;
import com.example.COLLEGE_MANAGEMENT.services.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public SubjectServiceImpl(SubjectRepository subjectRepository,
                              ProfessorRepository professorRepository,
                              StudentRepository studentRepository,
                              ModelMapper modelMapper) {
        this.subjectRepository = subjectRepository;
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    //  Create Subject
    @Override
    public SubjectDTO createSubject(SubjectDTO dto) {
        SubjectEntity subject = modelMapper.map(dto, SubjectEntity.class);
        SubjectEntity saved = subjectRepository.save(subject);
        return convertToDTO(saved);
    }

    //  Get by ID
    @Override
    public SubjectDTO getSubjectById(Long id) {
        SubjectEntity subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + id));
        return convertToDTO(subject);
    }

    //  Get All
    @Override
    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //  Delete
    @Override
    public void deleteSubject(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Subject not found with id: " + id);
        }
        subjectRepository.deleteById(id);
    }

    //  Assign Professor (Many-to-One)
    @Override
    public SubjectDTO assignProfessor(Long subjectId, Long professorId) {
        SubjectEntity subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));
        ProfessorEntity professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found"));
        subject.setProfessor(professor);
        return convertToDTO(subjectRepository.save(subject));
    }

    //  Assign Students (Many-to-Many)
    @Override
    public SubjectDTO assignStudents(Long subjectId, List<Long> studentIds) {
        SubjectEntity subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));
        List<StudentEntity> students = studentRepository.findAllById(studentIds);
        subject.setStudents(students);
        return convertToDTO(subjectRepository.save(subject));
    }

    //  Entity → DTO
    private SubjectDTO convertToDTO(SubjectEntity entity) {
        SubjectDTO dto = modelMapper.map(entity, SubjectDTO.class);

        if (entity.getProfessor() != null) {
            dto.setProfessorId(entity.getProfessor().getId());
        }
        if (entity.getStudents() != null) {
            dto.setStudentIds(
                    entity.getStudents().stream().map(StudentEntity::getId).toList()
            );
        }
        return dto;
    }
}