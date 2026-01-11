package com.example.vn2_ht_student.repository;

import com.example.vn2_ht_student.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    long countByGroupId(Long groupId);
    List<Task> findByGroupId(Long groupId);
}