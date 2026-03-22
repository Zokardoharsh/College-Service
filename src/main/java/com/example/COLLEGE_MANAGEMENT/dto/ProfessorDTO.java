package com.example.COLLEGE_MANAGEMENT.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfessorDTO {

    private Long id;
    private String title;

    private List<Long> subjectIds;
    private List<Long> studentIds;

    // getters and setters
}