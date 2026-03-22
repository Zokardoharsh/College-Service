package com.example.COLLEGE_MANAGEMENT.services;

import com.example.COLLEGE_MANAGEMENT.dto.StudentDTO;
import com.example.COLLEGE_MANAGEMENT.entities.ProfessorEntity;
import com.example.COLLEGE_MANAGEMENT.entities.StudentEntity;
import com.example.COLLEGE_MANAGEMENT.entities.SubjectEntity;
import com.example.COLLEGE_MANAGEMENT.exceptions.ResourceNotFoundException;
import com.example.COLLEGE_MANAGEMENT.repositories.ProfessorRepository;
import com.example.COLLEGE_MANAGEMENT.repositories.StudentRepository;
import com.example.COLLEGE_MANAGEMENT.repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ProfessorRepository professorRepository;
    private final ModelMapper modelMapper;

    public StudentServiceImpl(StudentRepository studentRepository,
                              SubjectRepository subjectRepository,
                              ProfessorRepository professorRepository,
                              ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.professorRepository = professorRepository;
        this.modelMapper = modelMapper;
    }

    // ✅ Create Student
    @Override
    public StudentDTO createStudent(StudentDTO dto) {
        StudentEntity student = modelMapper.map(dto, StudentEntity.class);
        StudentEntity saved = studentRepository.save(student);
        return convertToDTO(saved);
    }

    // ✅ Get by ID
    @Override
    public StudentDTO getStudentById(Long id) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return convertToDTO(student);
    }

    // ✅ Get All
    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Delete
    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    // ✅ Assign Subjects
    @Override
    public StudentDTO assignSubjects(Long studentId, List<Long> subjectIds) {
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        List<SubjectEntity> subjects = subjectRepository.findAllById(subjectIds);
        student.setSubjects(subjects);
        return convertToDTO(studentRepository.save(student));
    }

    // ✅ Assign Professors
    @Override
    public StudentDTO assignProfessors(Long studentId, List<Long> professorIds) {
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        List<ProfessorEntity> professors = professorRepository.findAllById(professorIds);
        student.setProfessors(professors);
        return convertToDTO(studentRepository.save(student));
    }

    // 🔄 Entity → DTO
    private StudentDTO convertToDTO(StudentEntity entity) {
        StudentDTO dto = modelMapper.map(entity, StudentDTO.class);
        if (entity.getSubjects() != null) {
            dto.setSubjectIds(
                    entity.getSubjects().stream().map(SubjectEntity::getId).toList()
            );
        }
        if (entity.getProfessors() != null) {
            dto.setProfessorIds(
                    entity.getProfessors().stream().map(ProfessorEntity::getId).toList()
            );
        }
        if (entity.getAdmissionRecord() != null) {
            dto.setAdmissionRecordId(entity.getAdmissionRecord().getId());
        }
        return dto;
    }
}
