package com.example.vn2_ht_student.repository.custome;

public interface TaskAssignmentCustomRepository {
    long countOnTimeCompletedTasks(Long groupId, Long userId);
    long countCompletedTasks(Long groupId, Long userId);

}
