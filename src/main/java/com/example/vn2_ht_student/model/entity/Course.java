package com.example.vn2_ht_student.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@Setter
@Table(name = "COURSES")
public class Course extends  BaseCreatedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_ID")
    private Long id;
    @Column(name = "COURSE_NAME", nullable = false)
    private String courseName;

    @Column(name = "SEMESTER")
    private String semester;

    @Column(name = " EXPIRATION")
    private LocalDateTime expiration;
}
