package com.example.vn2_ht_student.model.dto.request;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TaskCreateRequest {
    private Long groupId;
    private String taskName;
    private String taskDescription;
    private LocalDateTime deadline;
}
