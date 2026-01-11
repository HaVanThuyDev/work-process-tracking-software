package com.example.vn2_ht_student.service;

import com.example.vn2_ht_student.model.dto.request.PeerEvaluationRequest;

public interface PeerEvaluationService {
    void submitEvaluation(PeerEvaluationRequest request, Long evaluatorId);
}
