package com.example.vn2_ht_student.repository;

import com.example.vn2_ht_student.model.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
    List<ActivityLog> findByGroupIdOrderByActionTimeDesc(Long groupId);
    List<ActivityLog> findByUserIdOrderByActionTimeDesc(Long userId);
}
