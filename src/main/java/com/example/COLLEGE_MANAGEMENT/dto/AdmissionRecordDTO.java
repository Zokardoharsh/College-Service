package com.example.COLLEGE_MANAGEMENT.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdmissionRecordDTO {

    private Long id;
    private Integer fees;

    private Long studentId;

    // getters and setters
}