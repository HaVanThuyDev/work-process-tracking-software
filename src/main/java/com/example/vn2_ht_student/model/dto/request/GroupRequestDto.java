package com.example.vn2_ht_student.model.dto.request;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GroupRequestDto {
    private  String groupName;
    private Long courseId;
    private LocalDateTime createdAt;
}
