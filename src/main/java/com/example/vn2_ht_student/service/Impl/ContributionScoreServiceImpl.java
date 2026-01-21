package com.example.vn2_ht_student.service.Impl;

import com.example.vn2_ht_student.model.dto.reponse.ContributionRankingResponse;
import com.example.vn2_ht_student.model.entity.*;
import com.example.vn2_ht_student.repository.*;
import com.example.vn2_ht_student.service.ContributionScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContributionScoreServiceImpl implements ContributionScoreService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final TaskRepository taskRepository;
    private final TaskAssignmentRepository taskAssignmentRepository;
    private final ContributionScoreRepository contributionScoreRepository;
    @Override
    public void calculateContribution(Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));
        long totalTasks = taskRepository.countByGroupId(groupId);
        if (totalTasks == 0) {
            throw new RuntimeException("Group has no tasks");
        }
        List<GroupMember> members = groupMemberRepository.findByGroup_Id(groupId);
        int maxActivity = Math.toIntExact(groupMemberRepository.findMaxParticipationByGroupId(groupId));
        if (maxActivity == 0) maxActivity = 1;
        for (GroupMember member  : members) {

            User user = member .getUser();
            Long completedTasks = taskAssignmentRepository.countCompletedTasks(groupId, user.getId());
            double taskCompletionScore = (double) completedTasks / totalTasks;
            long onTimeTasks = taskAssignmentRepository.countOnTimeCompletedTasks(groupId, user.getId());
            double ontimeScore = completedTasks == 0 ? 0 : (double) onTimeTasks / completedTasks;

            double avgPeerReview = contributionScoreRepository.calculateAveragePeerReview(groupId, user.getId());
            double peerReviewScore = avgPeerReview / 5.0;
            double totalContributionScore =
                    taskCompletionScore * 0.4 +
                    ontimeScore * 0.2 +
                    peerReviewScore * 0.2;
            ContributionScore score = (ContributionScore) contributionScoreRepository.findAllByGroupId(groupId);
            score.setGroup(group);
            score.setUser(user);
            score.setTaskCompletionScore((float) taskCompletionScore);
            score.setOntimeScore((float) ontimeScore);
            score.setPeerReviewScore((float) peerReviewScore);
            score.setTotalContributionScore((float) totalContributionScore);
            score.setCalculatedAt(LocalDateTime.now());
            contributionScoreRepository.save(score);
        }

    }

    @Override
    public List<ContributionScore> getContributionScoresByGroup(Long groupId) {
        if (!groupRepository.existsById(groupId)) {
            throw new RuntimeException("Group not found");
        }
        return contributionScoreRepository.findAllByGroupId(groupId);
    }


    @Override
    public List<ContributionRankingResponse> getContributionRanking(Long groupId) {

        if (!groupRepository.existsById(groupId)) {
            throw new RuntimeException("Group not found");
        }
        List<Object[]> results = contributionScoreRepository.getContributionRanking(groupId);
        return results.stream().map(obj -> new ContributionRankingResponse(
                        (Long) obj[0],
                        (String) obj[1],
                        (Float) obj[2])).toList();
    }
}
