package com.example.vn2_ht_student.repository;

import com.example.vn2_ht_student.model.entity.ContributionScore;
import com.example.vn2_ht_student.repository.custome.ContributionScoreCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContributionScoreRepository extends JpaRepository<ContributionScore, Long>, ContributionScoreCustomRepository {

    Optional<ContributionScore> findByGroupIdAndUserId(Long groupId, Long userId);
    List<ContributionScore> findAllByGroupId(Long groupId);
    List<ContributionScore> findAllByGroupIdOrderByTotalContributionScoreDesc(Long groupId);
}

