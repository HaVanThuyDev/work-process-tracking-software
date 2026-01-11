package com.example.vn2_ht_student.repository;

import com.example.vn2_ht_student.model.entity.ContributionScore;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ContributionRepository extends JpaRepository<ContributionScore, Long> {
    Optional<ContributionScore> findByUserIdAndId(Long userId, Long id);
}
