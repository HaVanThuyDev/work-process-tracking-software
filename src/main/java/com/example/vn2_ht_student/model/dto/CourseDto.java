package com.example.vn2_ht_student.model.dto;

import com.example.vn2_ht_student.model.entity.Course;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data

@Getter
@Setter
public class CourseDto {
    private String courseName;
    private String semester;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime expiration;

    public  CourseDto(Course course) {
        this.courseName = course.getCourseName();
        this.semester = course.getSemester();
        this.createdAt =course.getCreatedAt();
        this.createdBy = course.getCreatedBy();
        this.expiration =course.getExpiration();

    }
}
