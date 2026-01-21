package com.example.vn2_ht_student.service;

import com.example.vn2_ht_student.model.dto.reponse.TaskResponseDto;
import com.example.vn2_ht_student.model.dto.request.AssignTaskRequest;
import com.example.vn2_ht_student.model.dto.request.TaskCreateRequest;
import com.example.vn2_ht_student.model.dto.request.UpdateTaskStatusRequest;
import java.util.List;

public interface TaskService {
    TaskResponseDto createTask(TaskCreateRequest request);
    void assignTask(AssignTaskRequest request, Long userId);
    TaskResponseDto updateTaskStatus(Long taskId, UpdateTaskStatusRequest request, Long userId);
    List<TaskResponseDto> getTasksByGroup(Long groupId);
}
