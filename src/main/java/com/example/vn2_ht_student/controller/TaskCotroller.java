package com.example.vn2_ht_student.controller;

import com.example.vn2_ht_student.model.dto.reponse.TaskResponseDto;
import com.example.vn2_ht_student.model.dto.request.AssignTaskRequest;
import com.example.vn2_ht_student.model.dto.request.TaskCreateRequest;
import com.example.vn2_ht_student.model.dto.request.UpdateTaskStatusRequest;
import com.example.vn2_ht_student.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskCotroller {

    private final TaskService taskService;

    @PostMapping("/create")
    public TaskResponseDto createTask(@RequestBody TaskCreateRequest request, @RequestParam Long userId) {
        return taskService.createTask(request, userId);
    }
    @PostMapping("/assign")
    public void assignTask(@RequestBody AssignTaskRequest request, @RequestParam Long userId) {
        taskService.assignTask(request, userId);
    }
    @PostMapping("/{taskId}/status")
    public TaskResponseDto updateStatus(@PathVariable Long taskId, @RequestBody UpdateTaskStatusRequest request, @RequestParam Long userId) {
        return taskService.updateTaskStatus(taskId, request, userId);
    }
    @GetMapping("/group/{groupId}")
    public List<TaskResponseDto> getTasksByGroup(@PathVariable Long groupId) {
        return taskService.getTasksByGroup(groupId);
    }
}
