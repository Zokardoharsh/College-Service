package com.example.COLLEGE_MANAGEMENT.util;

import com.example.COLLEGE_MANAGEMENT.dto.DepartmentDTO;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {

    private String timeStamp;
    private DepartmentDTO data;
    private Object error;

    // getters & setters
}