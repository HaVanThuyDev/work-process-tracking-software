package com.example.vn2_ht_student.model.dto.request;

import com.example.vn2_ht_student.model.dto.PeerScoreDto;
import lombok.Data;

import java.util.List;
@Data
public class PeerEvaluationRequest {
    private Long groupId;
    private Long evaluatedUserId;
    private List<PeerScoreDto> scores;
}
