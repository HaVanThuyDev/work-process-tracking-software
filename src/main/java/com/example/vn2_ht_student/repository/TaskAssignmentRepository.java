package com.example.vn2_ht_student.repository;

import com.example.vn2_ht_student.model.entity.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Long> {
    List<TaskAssignment> findByTaskId(Long taskId);
    long countByTask_Group_IdAndUser_Id(Long groupId, Long userId);

}