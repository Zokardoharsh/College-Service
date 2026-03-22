package com.example.COLLEGE_MANAGEMENT.controllers;

import com.example.COLLEGE_MANAGEMENT.dto.AdmissionRecordDTO;
import com.example.COLLEGE_MANAGEMENT.services.AdmissionRecordService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admission-records")
public class AdmissionRecordController {

    private final AdmissionRecordService admissionRecordService;

    public AdmissionRecordController(AdmissionRecordService admissionRecordService) {
        this.admissionRecordService = admissionRecordService;
    }

    // ✅ Create Admission Record
    @PostMapping
    public AdmissionRecordDTO createAdmissionRecord(@Valid @RequestBody AdmissionRecordDTO dto) {
        return admissionRecordService.createAdmissionRecord(dto);
    }

    // ✅ Get by ID
    @GetMapping("/{id}")
    public AdmissionRecordDTO getAdmissionRecordById(@PathVariable Long id) {
        return admissionRecordService.getAdmissionRecordById(id);
    }

    // ✅ Get All
    @GetMapping
    public List<AdmissionRecordDTO> getAllAdmissionRecords() {
        return admissionRecordService.getAllAdmissionRecords();
    }

    // ✅ Delete
    @DeleteMapping("/{id}")
    public void deleteAdmissionRecord(@PathVariable Long id) {
        admissionRecordService.deleteAdmissionRecord(id);
    }

    // ✅ Assign Student (One-to-One)
    @PutMapping("/{id}/student")
    public AdmissionRecordDTO assignStudent(
            @PathVariable Long id,
            @RequestParam Long studentId
    ) {
        return admissionRecordService.assignStudent(id, studentId);
    }
}