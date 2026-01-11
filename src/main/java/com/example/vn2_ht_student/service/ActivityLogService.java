package com.example.vn2_ht_student.service;


import com.example.vn2_ht_student.model.entity.ActivityLog;
import com.example.vn2_ht_student.model.entity.Group;
import com.example.vn2_ht_student.model.entity.User;
import java.util.List;

public interface ActivityLogService {
    void log(User user, Group group, String actionType, String actionDetail);
    List<ActivityLog> getLogsByGroup(Long groupId);
    List<ActivityLog> getLogsByUser(Long userId);
}