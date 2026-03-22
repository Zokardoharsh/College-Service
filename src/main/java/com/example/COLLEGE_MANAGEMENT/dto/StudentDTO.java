package com.example.COLLEGE_MANAGEMENT.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {

    private Long id;
    private String name;

    private List<Long> professorIds;
    private List<Long> subjectIds;

    private Long admissionRecordId;

    // getters and setters
}
