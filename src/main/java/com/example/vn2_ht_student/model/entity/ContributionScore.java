package com.example.vn2_ht_student.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "CONTRIBUTION_SCORES")
public class ContributionScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCORE_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "TASK_COMPLETION_SCORE")
    private Float taskCompletionScore;

    @Column(name = "ONTIME_SCORE")
    private Float ontimeScore;

    @Column(name = "ACTIVITY_SCORE")
    private Float activityScore;

    @Column(name = "PEER_REVIEW_SCORE")
    private Float peerReviewScore;

    @Column(name = "TOTAL_CONTRIBUTION_SCORE")
    private Float totalContributionScore;

    @Column(name = "CALCULATED_AT")
    private LocalDateTime calculatedAt;
}
