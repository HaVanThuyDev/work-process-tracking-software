package com.example.vn2_ht_student.model.dto.request;

import com.example.vn2_ht_student.model.enums.TaskStatus;
import lombok.Data;

@Data
public class UpdateTaskStatusRequest {
    private TaskStatus status;
}