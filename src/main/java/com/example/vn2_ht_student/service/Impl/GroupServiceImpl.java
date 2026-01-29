package com.example.vn2_ht_student.service.Impl;

import com.example.vn2_ht_student.model.dto.reponse.GroupResponseDto;
import com.example.vn2_ht_student.model.dto.request.GroupRequestDto;
import com.example.vn2_ht_student.model.entity.Course;
import com.example.vn2_ht_student.model.entity.Group;
import com.example.vn2_ht_student.model.entity.GroupMember;
import com.example.vn2_ht_student.model.entity.User;
import com.example.vn2_ht_student.repository.CourseRepository;
import com.example.vn2_ht_student.repository.GroupMemberRepository;
import com.example.vn2_ht_student.repository.GroupRepository;
import com.example.vn2_ht_student.repository.UserRepository;
import com.example.vn2_ht_student.security.SecurityUtils;
import com.example.vn2_ht_student.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.example.vn2_ht_student.repository.custome.GroupMemberCustomRepository;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Override
    public GroupResponseDto create(GroupRequestDto request) {
        Course course = courseRepository.findById(request.getCourseId()).orElseThrow(() -> new RuntimeException("Course not found"));
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) {
            throw new AccessDeniedException("Unauthenticated");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Group group = new Group();
        group.setGroupName(request.getGroupName());
        group.setCourse(course);
        group.setCreatedAt(LocalDateTime.now());
        Group saved = groupRepository.save(group);
        GroupMember leader = new GroupMember();
        leader.setGroup(saved);
        course.setCreatedBy(String.valueOf(userId));
        groupMemberRepository.save(leader);
        return mapToDto(saved);
    }

    @Override
    public List<GroupResponseDto> getAllGroups() {
        return groupRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    private GroupResponseDto mapToDto(Group group) {

        GroupResponseDto dto = new GroupResponseDto();
        dto.setGroupName(group.getGroupName());
        dto.setCreatedAt(group.getCreatedAt());
        dto.setCreatedBy(group.getCreatedBy());
        Object[] leaderInfo = groupMemberRepository.findLeaderNameByGroupId(group.getId());
        if (leaderInfo != null) {
            dto.setLeaderName((String) leaderInfo[0]);
            dto.setClassName((String) leaderInfo[1]);
        }

        return dto;
    }

    @Override
    public GroupResponseDto update(Long groupId, GroupRequestDto request) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        group.setGroupName(request.getGroupName());
        Group updated = groupRepository.save(group);
        return mapToDto(updated);
    }
    @Override
    public void delete(Long groupId) {
        if (!groupRepository.existsById(groupId)) {
            throw new RuntimeException("Group not found");
        }
        groupRepository.deleteById(groupId);
    }

}
