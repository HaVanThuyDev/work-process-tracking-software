package com.example.vn2_ht_student.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@Setter
@Table(name = "ACTIVITY_LOGS")
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOG_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @Column(name = "ACTION_TYPE")
    private String actionType;

    @Column(name = "ACTION_DETAIL")
    private String actionDetail;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "ACTION_TIME")
    private LocalDateTime actionTime;
}
