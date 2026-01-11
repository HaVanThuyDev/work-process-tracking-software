package com.example.vn2_ht_student.service.Impl;

import com.example.vn2_ht_student.model.entity.*;
import com.example.vn2_ht_student.repository.*;
import com.example.vn2_ht_student.service.ContributionScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContributionScoringServiceImpl implements ContributionScoreService {

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
        for (GroupMember member : members) {
            User user = member.getUser();
            long assignedTasks = taskAssignmentRepository.countByTask_Group_IdAndUser_Id(groupId, user.getId());
            double score = (double) assignedTasks / totalTasks;
            ContributionScore contributionScore = contributionScoreRepository.findByGroupIdAndUserId(groupId, user.getId()).orElse(new ContributionScore());
            contributionScore.setGroup(group);
            contributionScore.setUser(user);
            contributionScore.setOntimeScore((float) score);
            contributionScoreRepository.save(contributionScore);
        }
    }
}
