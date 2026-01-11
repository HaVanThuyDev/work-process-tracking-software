package com.example.vn2_ht_student.model.entity;

import com.example.vn2_ht_student.model.enums.Status;
import com.example.vn2_ht_student.model.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TASKS")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASK_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @Column(name = "TASK_NAME", nullable = false)
    private String taskName;

    @Column(name = "TASK_DESCRIPTION")
    private String taskDescription;

    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    private User createdBy;

    @Column(name = "DEADLINE")
    private LocalDateTime deadline;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}
