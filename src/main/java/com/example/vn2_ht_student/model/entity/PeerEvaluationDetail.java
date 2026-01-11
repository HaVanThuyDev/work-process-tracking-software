package com.example.vn2_ht_student.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
@Table(name = "PEER_EVALUATION_DETAILS")
public class PeerEvaluationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DETAIL_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "EVALUATION_ID")
    private PeerEvaluation evaluation;

    @Column(name = "CRITERIA_NAME")
    private String criteriaName;

    @Column(name = "SCORE")
    private Integer score;
}
