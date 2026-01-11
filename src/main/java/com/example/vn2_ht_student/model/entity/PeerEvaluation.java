package com.example.vn2_ht_student.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "PEER_EVALUATIONS")
public class PeerEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVALUATION_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "EVALUATOR_ID")
    private User evaluator;

    @ManyToOne
    @JoinColumn(name = "EVALUATED_ID")
    private User evaluated;

    @Column(name = "EVALUATION_DATE")
    private LocalDateTime evaluationDate;
}
