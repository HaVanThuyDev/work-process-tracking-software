package com.example.vn2_ht_student.repository.custome;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class GroupMemberCustomRepositoryImpl implements GroupMemberCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int findMaxParticipationByGroupId(Long groupId) {

        String jpql = """
            SELECT COALESCE(MAX(gm.participationCount), 0)
            FROM GroupMember gm
            WHERE gm.group.id = :groupId
        """;

        Integer result = entityManager
                .createQuery(jpql, Integer.class)
                .setParameter("groupId", groupId)
                .getSingleResult();
        return result == null ? 0 : result;
    }
}
