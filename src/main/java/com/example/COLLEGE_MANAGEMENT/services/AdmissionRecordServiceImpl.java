package com.example.COLLEGE_MANAGEMENT.services;

import com.example.COLLEGE_MANAGEMENT.dto.AdmissionRecordDTO;
import com.example.COLLEGE_MANAGEMENT.entities.*;
import com.example.COLLEGE_MANAGEMENT.exceptions.ResourceNotFoundException;
import com.example.COLLEGE_MANAGEMENT.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdmissionRecordServiceImpl implements AdmissionRecordService {

    private final AdmissionRecordRepository admissionRecordRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public AdmissionRecordServiceImpl(AdmissionRecordRepository admissionRecordRepository,
                                      StudentRepository studentRepository,
                                      ModelMapper modelMapper) {
        this.admissionRecordRepository = admissionRecordRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    //  Create
    @Override
    public AdmissionRecordDTO createAdmissionRecord(AdmissionRecordDTO dto) {
        AdmissionRecordEntity record = modelMapper.map(dto, AdmissionRecordEntity.class);
        AdmissionRecordEntity saved = admissionRecordRepository.save(record);
        return convertToDTO(saved);
    }

    //  Get by ID
    @Override
    public AdmissionRecordDTO getAdmissionRecordById(Long id) {
        AdmissionRecordEntity record = admissionRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admission record not found with id: " + id));
        return convertToDTO(record);
    }

    //  Get All
    @Override
    public List<AdmissionRecordDTO> getAllAdmissionRecords() {
        return admissionRecordRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //  Delete
    @Override
    public void deleteAdmissionRecord(Long id) {
        if (!admissionRecordRepository.existsById(id)) {
            throw new ResourceNotFoundException("Admission record not found with id: " + id);
        }
        admissionRecordRepository.deleteById(id);
    }

    //  Assign Student (One-to-One)
    @Override
    public AdmissionRecordDTO assignStudent(Long admissionRecordId, Long studentId) {

        AdmissionRecordEntity record = admissionRecordRepository.findById(admissionRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("Admission record not found"));

        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        record.setStudent(student);

        return convertToDTO(admissionRecordRepository.save(record));
    }

    //  Entity → DTO
    private AdmissionRecordDTO convertToDTO(AdmissionRecordEntity entity) {
        AdmissionRecordDTO dto = modelMapper.map(entity, AdmissionRecordDTO.class);

        if (entity.getStudent() != null) {
            dto.setStudentId(entity.getStudent().getId());
        }

        return dto;
    }
}