package com.example.vn2_ht_student.repository;

import com.example.vn2_ht_student.model.entity.PeerEvaluationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeerEvaluationDetailRepository extends JpaRepository<PeerEvaluationDetail, Long> {
    List<PeerEvaluationDetail> findByEvaluation(Long studentId);
}
