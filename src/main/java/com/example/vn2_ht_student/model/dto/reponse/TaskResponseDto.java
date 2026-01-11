
package com.example.vn2_ht_student.model.dto.reponse;

import com.example.vn2_ht_student.model.enums.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TaskResponseDto {
    private Long id;
    private String taskName;
    private String taskDescription;
    private TaskStatus status;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;
    private SimpleUserResponse createdBy;
    private SimpleGroupResponse group;

}
