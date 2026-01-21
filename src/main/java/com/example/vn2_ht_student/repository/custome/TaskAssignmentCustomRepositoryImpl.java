package com.example.vn2_ht_student.repository.custome;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class TaskAssignmentCustomRepositoryImpl
        implements TaskAssignmentCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long countCompletedTasks(Long groupId, Long userId) {

        String jpql = """
            SELECT COUNT(db)
            FROM TaskAssignment db
            WHERE db.task.group.id = :groupId
              AND db.user.id = :userId
              AND db.status = 'DONE'
        """;
        Long result = entityManager
                .createQuery(jpql, Long.class)
                .setParameter("groupId", groupId)
                .setParameter("userId", userId)
                .getSingleResult();

        return result == null ? 0L : result;
    }

    @Override
    public long countOnTimeCompletedTasks(Long groupId, Long userId) {

        String jpql = """
            SELECT COUNT(ta)
            FROM TaskAssignment ta
            WHERE ta.task.group.id = :groupId
              AND ta.user.id = :userId
              AND ta.status = 'DONE'
              AND ta.completedAt <= ta.task.deadline
        """;

        Long result = entityManager
                .createQuery(jpql, Long.class)
                .setParameter("groupId", groupId)
                .setParameter("userId", userId)
                .getSingleResult();

        return result == null ? 0L : result;
    }
}
