package com.example.vn2_ht_student.repository;

import com.example.vn2_ht_student.model.entity.PeerEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeerReviewRepository extends JpaRepository<PeerEvaluation, Long> {

}
