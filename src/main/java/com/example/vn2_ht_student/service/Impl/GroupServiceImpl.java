package com.example.vn2_ht_student.service.Impl;

import com.example.vn2_ht_student.model.dto.reponse.GroupMemberResponseDto;
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
import com.example.vn2_ht_student.security.annotation.PermissionCheck;
import com.example.vn2_ht_student.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import static com.example.vn2_ht_student.model.enums.Action.*;
import static com.example.vn2_ht_student.model.enums.Role.*;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private com.example.vn2_ht_student.model.dto.request.GroupRequestDto GroupRequestDto;

    @Override
    public GroupResponseDto create(GroupRequestDto request, Long creatorId) {
        Course course = courseRepository.findById(request.getCourseId()).orElseThrow(() -> new RuntimeException("Course not found"));
        User creator = userRepository.findById(creatorId).orElseThrow(() -> new RuntimeException("User not found"));
        Group group = new Group();
        group.setGroupName(request.getGroupName());
        group.setCourse(course);
        group.setCreatedAt(LocalDateTime.now());
        Group saved = groupRepository.save(group);
        GroupMember leader = new GroupMember();
        leader.setGroup(saved);
        leader.setUser(creator);
        leader.setRoleInGroup("LEADER");
        leader.setJoinedAt(LocalDateTime.now());
        groupMemberRepository.save(leader);
        return mapToDto(saved);
    }

    @Override
    public GroupMemberResponseDto add(Long groupId, Long userId, String role) {

        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        GroupMember member = new GroupMember();
        member.setGroup(group);
        member.setUser(user);
        member.setRoleInGroup(role);
        member.setJoinedAt(LocalDateTime.now());
        GroupMember saved = groupMemberRepository.save(member);
        GroupMemberResponseDto dto = new GroupMemberResponseDto();
        dto.setGroupId(saved.getGroup().getId());
        dto.setUserId(saved.getUser().getId());
        dto.setRoleInGroup(saved.getRoleInGroup());
        dto.setJoinedAt(saved.getJoinedAt());
        return dto;
    }


    @Override
    public List<GroupResponseDto> getGroupsByCourse(Long courseId) {
       Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
       List<Group> gr = groupRepository.findByCourseId(courseId);
        return gr.stream().map(group -> {
            GroupResponseDto dto = new GroupResponseDto();
            dto.setId(group.getId());
            dto.setGroupName(group.getGroupName());
            dto.setCourseId(course.getId());
            return dto;
        }).collect(Collectors.toList());
    }

    private GroupResponseDto mapToDto(Group group) {
        GroupResponseDto dto = new GroupResponseDto();
        dto.setId(group.getId());
        dto.setGroupName(group.getGroupName());
        dto.setCourseId(group.getCourse().getId());
        return dto;
    }
}
