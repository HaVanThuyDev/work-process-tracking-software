package com.example.vn2_ht_student.model.dto.reponse;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponseDto {
    private String groupName;
    private LocalDateTime createdAt;
    private String createdBy;
    private String leaderName;
    private String className;

}