package com.example.vn2_ht_student.service.Impl;

import com.example.vn2_ht_student.model.dto.reponse.ActivityLogResponseDto;
import com.example.vn2_ht_student.model.dto.reponse.SimpleUserResponse;
import com.example.vn2_ht_student.model.entity.ActivityLog;
import com.example.vn2_ht_student.model.entity.Group;
import com.example.vn2_ht_student.model.entity.User;
import com.example.vn2_ht_student.repository.ActivityLogRepository;
import com.example.vn2_ht_student.repository.GroupRepository;
import com.example.vn2_ht_student.repository.UserRepository;
import com.example.vn2_ht_student.service.ActivityLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {

    private final ActivityLogRepository activityLogRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;


    @Override
    public void log(User user, Group group, String actionType, String actionDetail) {
        User user1 = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        Group group1 = groupRepository.findById(group.getId()).orElseThrow(() -> new RuntimeException("Group not found"));
        ActivityLog log = new ActivityLog();
        log.setUser(user);
        log.setGroup(group);
        log.setActionType(actionType);
        log.setActionDetail(actionDetail);
        log.setActionTime(LocalDateTime.now());
        activityLogRepository.save(log);
    }

    @Override
    public List<ActivityLog> getLogsByGroup(Long groupId) {
        return activityLogRepository.findByGroupIdOrderByActionTimeDesc(groupId);
    }

    @Override
    public List<ActivityLog> getLogsByUser(Long userId) {
        return activityLogRepository.findByUserIdOrderByActionTimeDesc(userId);
    }
    private ActivityLogResponseDto mapToDto(ActivityLog log) {
        ActivityLogResponseDto dto = new ActivityLogResponseDto();
        dto.setId(log.getId());
        dto.setActionType(log.getActionType());
        dto.setActionDetail(log.getActionDetail());
        dto.setActionTime(log.getActionTime());
        SimpleUserResponse user = new SimpleUserResponse();
        user.setId(log.getUser().getId());
        user.setFullName(log.getUser().getFullName());
        user.setMsv(log.getUser().getMsv());
        dto.setUser(user);
        return dto;
    }
}
