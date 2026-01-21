package com.example.vn2_ht_student.model.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContributionRankingResponse {
    private Long userId;
    private String fullName;
    private Float totalContributionScore;
}

