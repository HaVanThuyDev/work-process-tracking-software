package com.example.vn2_ht_student.repository.custome;

import java.util.List;

public interface ContributionScoreCustomRepository {
    Double calculateAveragePeerReview(Long groupId, Long userId);
    List<Object[]> getContributionRanking(Long groupId);
}
