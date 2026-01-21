package com.example.vn2_ht_student.controller;

import com.example.vn2_ht_student.Utils.Constants;
import com.example.vn2_ht_student.model.dto.reponse.ResponseDTO;
import com.example.vn2_ht_student.model.dto.request.AssignTaskRequest;
import com.example.vn2_ht_student.model.dto.request.TaskCreateRequest;
import com.example.vn2_ht_student.model.dto.request.UpdateTaskStatusRequest;
import com.example.vn2_ht_student.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskCotroller {

    private final TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody TaskCreateRequest request) {
        taskService.createTask(request);
        return ResponseEntity.ok(ResponseDTO.builder()
                .status("oke")
                .code(Constants.HTTP_STATUS.SUCCESS)
                .message("thêm nhiệm vụ thành công  ")
                .build()
        );
    }
    @PostMapping("/assign")
    public ResponseEntity<?>assignTask(@RequestBody AssignTaskRequest request, @RequestParam Long userId) {
        try {
            taskService.assignTask(request, userId);
            return ResponseEntity.ok(ResponseDTO.builder()
                    .status("ok")
                    .code(Constants.HTTP_STATUS.SUCCESS)
                    .message("giao nhiệm vụ thành công ")
                    .build()
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDTO.builder()
                            .status("error")
                            .code(Constants.HTTP_STATUS.INTERNAL_SERVER_ERROR)
                            .data(null)
                            .message(e.getMessage())
                            .build());
        }
    }
    @PostMapping("/{taskId}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long taskId, @RequestBody UpdateTaskStatusRequest request, @RequestParam Long userId) {
        try {
            taskService.updateTaskStatus(taskId, request, userId);
            return ResponseEntity.ok(ResponseDTO.builder()
                    .status("ok")
                    .code(Constants.HTTP_STATUS.SUCCESS)
                    .message("cập nhật nhiệm vụ thành công ")
                    .build()
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDTO.builder()
                            .status("error")
                            .code(Constants.HTTP_STATUS.INTERNAL_SERVER_ERROR)
                            .data(null)
                            .message(e.getMessage())
                            .build());
        }
    }
    @GetMapping("/group/{groupId}")
    public ResponseEntity<?> getTasksByGroup(@PathVariable Long groupId) {
        try {
            taskService.getTasksByGroup(groupId);
            return ResponseEntity.ok(ResponseDTO.builder()
                    .status("ok")
                    .code(Constants.HTTP_STATUS.SUCCESS)
                    .message("cập nhật nhiệm vụ thành công ")
                    .build()
            );

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDTO.builder()
                            .status("error")
                            .code(Constants.HTTP_STATUS.INTERNAL_SERVER_ERROR)
                            .data(null)
                            .message(e.getMessage())
                            .build());
        }
    }
}
