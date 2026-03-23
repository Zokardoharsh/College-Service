package com.example.COLLEGE_MANAGEMENT.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentDTO {

    private Long id;
    private String title;
    private boolean isActive;

    // getters & setters
}