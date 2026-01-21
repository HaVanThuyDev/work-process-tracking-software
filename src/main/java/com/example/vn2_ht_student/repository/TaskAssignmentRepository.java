package com.example.vn2_ht_student.repository;

import com.example.vn2_ht_student.model.entity.TaskAssignment;
import com.example.vn2_ht_student.repository.custome.TaskAssignmentCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Long>, TaskAssignmentCustomRepository {
}