package com.example.vn2_ht_student.controller;

import com.example.vn2_ht_student.model.entity.ActivityLog;
import com.example.vn2_ht_student.service.ActivityLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityLogController {
    private final ActivityLogService activityLogService;

    @GetMapping("/group/{groupId}")
    public List<ActivityLog> getLogsByGroup(@PathVariable Long groupId) {
        return activityLogService.getLogsByGroup(groupId);
    }
    @GetMapping("/user/{userId}")
    public List<ActivityLog> getLogsByUser(@PathVariable Long userId) {
        return activityLogService.getLogsByUser(userId);
    }
}
