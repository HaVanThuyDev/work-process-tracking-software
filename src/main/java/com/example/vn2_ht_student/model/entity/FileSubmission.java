package com.example.vn2_ht_student.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "FILE_SUBMISSIONS")
public class FileSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TASK_ID")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "SUBMITTED_AT")
    private LocalDateTime submittedAt;
}
