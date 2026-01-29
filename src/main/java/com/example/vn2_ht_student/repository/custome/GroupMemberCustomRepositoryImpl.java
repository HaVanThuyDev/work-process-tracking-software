package com.example.vn2_ht_student.repository.custome;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
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

    @Override
    public Object[] findLeaderNameByGroupId(Long groupId) {
        String sql = """
        SELECT u.FULL_NAME,u.CLASS_ID FROM GROUP_MEMBERS gm
        JOIN USERS u ON u.USER_ID = gm.USER_ID WHERE gm.GROUP_ID = :groupId 
        AND gm.ROLE_IN_GROUP = 'LEADER' LIMIT 1
    """;

        return (Object[]) entityManager
                .createNativeQuery(sql)
                .setParameter("groupId", groupId)
                .getSingleResult();
    }


}


