package com.example.vn2_ht_student.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "COURSES")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_ID")
    private Long id;
    @Column(name = "COURSE_NAME", nullable = false)
    private String courseName;
    @Column(name = "SEMESTER")
    private String semester;
    @ManyToOne
    @JoinColumn(name = "LECTURER_ID")
    private User lecturer;
}
