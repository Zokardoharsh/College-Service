package com.example.COLLEGE_MANAGEMENT.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectDTO {

    private Long id;
    private String title;

    private Long professorId;
    private List<Long> studentIds;

    // getters and setters
}
