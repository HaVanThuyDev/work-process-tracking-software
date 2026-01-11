package com.example.vn2_ht_student.model.dto.reponse;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GroupMemberResponseDto {
    private Long groupId;
    private Long userId;
    private String roleInGroup;
    private LocalDateTime joinedAt;
}
