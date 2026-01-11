package com.example.vn2_ht_student.repository;

import com.example.vn2_ht_student.model.entity.ContributionScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ContributionScoreRepository extends JpaRepository<ContributionScore, Long> {

    Optional<ContributionScore> findByGroupIdAndUserId(Long groupId, Long userId);
}
