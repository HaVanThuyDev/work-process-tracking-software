package com.example.vn2_ht_student.service.Impl;


import com.example.vn2_ht_student.model.dto.reponse.SimpleGroupResponse;
import com.example.vn2_ht_student.model.dto.reponse.SimpleUserResponse;
import com.example.vn2_ht_student.model.dto.reponse.TaskResponseDto;
import com.example.vn2_ht_student.model.dto.request.AssignTaskRequest;
import com.example.vn2_ht_student.model.dto.request.TaskCreateRequest;
import com.example.vn2_ht_student.model.dto.request.UpdateTaskStatusRequest;
import com.example.vn2_ht_student.model.entity.*;
import com.example.vn2_ht_student.model.enums.Action;
import com.example.vn2_ht_student.model.enums.ResourceType;
import com.example.vn2_ht_student.model.enums.Role;
import com.example.vn2_ht_student.model.enums.TaskStatus;
import com.example.vn2_ht_student.repository.*;
import com.example.vn2_ht_student.security.SecurityUtils;
import com.example.vn2_ht_student.security.annotation.PermissionCheck;
import com.example.vn2_ht_student.service.ActivityLogService;
import com.example.vn2_ht_student.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskAssignmentRepository taskAssignmentRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ActivityLogService activityLogService;


    @Override
    @PermissionCheck(roles = { Role.LEADER }, resource = ResourceType.TASK, action = Action.TASK_CREATE)
    public TaskResponseDto createTask(TaskCreateRequest request) {

        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) {
            throw new AccessDeniedException("Unauthenticated");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Group group = groupRepository.findById(request.getGroupId()).orElseThrow(() -> new RuntimeException("Group not found"));
        Task task = new Task();
        task.setGroup(group);
        task.setTaskName(request.getTaskName());
        task.setTaskDescription(request.getTaskDescription());
        task.setDeadline(request.getDeadline());
        task.setStatus(TaskStatus.TODO);
        task.setCreatedBy(user);
        task.setCreatedAt(LocalDateTime.now());
        Task saved = taskRepository.save(task);
        return mapToResponse(saved);
    }


    @Override
//    @PermissionCheck(resource = Role.LEADER, action = Action.TASK_ASSIGN)
    public void assignTask(AssignTaskRequest request, Long userId) {
        Task task = taskRepository.findById(request.getTaskId()).orElseThrow();
        User assignee = userRepository.findById(request.getUserId()).orElseThrow();
        TaskAssignment assignment = new TaskAssignment();
        assignment.setTask(task);
        assignment.setUser(assignee);
        assignment.setAssignedAt(LocalDateTime.now());
        taskAssignmentRepository.save(assignment);
        activityLogService.log(
                assignee,
                task.getGroup(),
                Action.TASK_ASSIGN.toString(),
                "Assigned to task: " + task.getTaskName()
        );
    }

    @Override
//    @PermissionCheck(resource = Role.LEADER, action = Action.TASK_UPDATE)
    public TaskResponseDto updateTaskStatus(Long taskId, UpdateTaskStatusRequest request, Long userId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(request.getStatus());
        task.setUpdatedAt(LocalDateTime.now());
        Task updated = taskRepository.save(task);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        activityLogService.log(
                user,
                task.getGroup(),
                Action.TASK_UPDATE_STATUS.toString(),
                "Updated task to " + request.getStatus()
        );
        return mapToResponse(updated);
    }


    @Override
//    @PermissionCheck(resource = Role.LEADER, action = Action.TASK_ASSIGN)
    public List<TaskResponseDto> getTasksByGroup(Long groupId) {
        return taskRepository.findByGroupId(groupId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
//    @PermissionCheck(resource = Role.LECTURER, action = Action.TASK_ATTACHMENT_VIEW)
    private TaskResponseDto mapToResponse(Task task) {
        TaskResponseDto res = new TaskResponseDto();
        res.setId(task.getId());
        res.setTaskName(task.getTaskName());
        res.setTaskDescription(task.getTaskDescription());
        res.setStatus(task.getStatus());
        res.setDeadline(task.getDeadline());
        res.setCreatedAt(task.getCreatedAt());
        SimpleUserResponse user = new SimpleUserResponse();
        user.setId(task.getCreatedBy().getId());
        user.setFullName(task.getCreatedBy().getFullName());
        user.setMsv(task.getCreatedBy().getMsv());
        res.setCreatedBy(user);
        SimpleGroupResponse group = new SimpleGroupResponse();
        group.setId(task.getGroup().getId());
        group.setGroupName(task.getGroup().getGroupName());
        res.setGroup(group);
        return res;
    }

}
