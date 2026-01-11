package com.example.vn2_ht_student.service.Impl;

import com.example.vn2_ht_student.model.dto.PeerScoreDto;
import com.example.vn2_ht_student.model.dto.request.PeerEvaluationRequest;
import com.example.vn2_ht_student.model.entity.Group;
import com.example.vn2_ht_student.model.entity.PeerEvaluation;
import com.example.vn2_ht_student.model.entity.PeerEvaluationDetail;
import com.example.vn2_ht_student.model.entity.User;
import com.example.vn2_ht_student.repository.GroupRepository;
import com.example.vn2_ht_student.repository.PeerEvaluationDetailRepository;
import com.example.vn2_ht_student.repository.PeerEvaluationRepository;
import com.example.vn2_ht_student.repository.UserRepository;
import com.example.vn2_ht_student.service.PeerEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PeerEvaluationServiceImpl implements PeerEvaluationService {

    private final PeerEvaluationRepository peerEvaluationRepository;
    private final PeerEvaluationDetailRepository detailRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Override
    public void submitEvaluation(PeerEvaluationRequest request, Long evaluatorId) {

        User evaluator = userRepository.findById(evaluatorId).orElseThrow();
        User evaluated = userRepository.findById(request.getEvaluatedUserId()).orElseThrow();
        Group group = groupRepository.findById(request.getGroupId()).orElseThrow();
        PeerEvaluation evaluation = new PeerEvaluation();
        evaluation.setEvaluator(evaluator);
        evaluation.setEvaluated(evaluated);
        evaluation.setGroup(group);
        evaluation.setEvaluationDate(LocalDateTime.now());
        PeerEvaluation saved = peerEvaluationRepository.save(evaluation);
        for (PeerScoreDto score : request.getScores()) {
            PeerEvaluationDetail detail = new PeerEvaluationDetail();
            detail.setEvaluation(saved);
            detail.setCriteriaName(score.getCriteria());
            detail.setScore(score.getScore());
            detailRepository.save(detail);
        }
    }
}
