package com.example.COLLEGE_MANAGEMENT.services;

import com.example.COLLEGE_MANAGEMENT.dto.AdmissionRecordDTO;

import java.util.List;

public interface AdmissionRecordService {

    AdmissionRecordDTO createAdmissionRecord(AdmissionRecordDTO dto);

    AdmissionRecordDTO getAdmissionRecordById(Long id);

    List<AdmissionRecordDTO> getAllAdmissionRecords();

    void deleteAdmissionRecord(Long id);

    AdmissionRecordDTO assignStudent(Long admissionRecordId, Long studentId);
}