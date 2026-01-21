package com.example.vn2_ht_student.service;

import com.example.vn2_ht_student.model.entity.ContributionScore;

import java.util.List;

public interface ContributionScoreService {
    void calculateContribution(Long groupId);
    List<ContributionScore> getContributionScoresByGroup(Long groupId);
    List<?> getContributionRanking(Long groupId);
}
