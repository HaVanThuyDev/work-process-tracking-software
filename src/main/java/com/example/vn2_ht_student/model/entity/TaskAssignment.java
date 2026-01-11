package com.example.vn2_ht_student.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Entity
@Setter
@Table(name = "TASK_ASSIGNMENTS")
public class TaskAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASSIGNMENT_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "TASK_ID")
    private Task task;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @Column(name = "ASSIGNED_AT")
    private LocalDateTime assignedAt;
}
