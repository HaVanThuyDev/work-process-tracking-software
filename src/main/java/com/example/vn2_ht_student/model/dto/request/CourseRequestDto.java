package com.example.vn2_ht_student.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
public class CourseRequestDto {
    private String courseName;
    private String semester;
    private LocalDate createdAt;
    private String createdBy;
    private LocalDate expiration;


}
