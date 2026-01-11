package com.example.vn2_ht_student.model.dto.reponse;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ActivityLogResponseDto {
    private Long id;
    private String actionType;
    private String actionDetail;
    private LocalDateTime actionTime;
    private SimpleUserResponse user;
}
