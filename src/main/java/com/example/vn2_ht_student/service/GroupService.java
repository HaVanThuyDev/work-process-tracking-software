package com.example.vn2_ht_student.service;

import com.example.vn2_ht_student.model.dto.reponse.GroupMemberResponseDto;
import com.example.vn2_ht_student.model.dto.reponse.GroupResponseDto;
import com.example.vn2_ht_student.model.dto.request.GroupRequestDto;
import java.util.List;

public interface GroupService {
    GroupResponseDto create(GroupRequestDto request);
    List<GroupResponseDto> getGroupsByCourse(Long courseId);

}
