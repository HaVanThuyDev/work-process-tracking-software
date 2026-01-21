package com.example.vn2_ht_student.repository.custome;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ContributionScoreCustomRepositoryImpl implements ContributionScoreCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Double calculateAveragePeerReview(Long groupId, Long userId) {

        String jpql = """
            SELECT (db.score)
            FROM PeerEvaluation db
            WHERE db.group.id = :groupId
              AND db.evaluated.id = :userId
        """;
        TypedQuery<Double> query = entityManager.createQuery(jpql, Double.class);
        query.setParameter("groupId", groupId);
        query.setParameter("userId", userId);

        return query.getSingleResult();
    }

    @Override
    public List<Object[]> getContributionRanking(Long groupId) {

        String jpql = """
            SELECT db.user.id,
                   db.user.fullName,
                   db.totalContributionScore
            FROM ContributionScore db
            WHERE db.group.id = :groupId
            ORDER BY db.totalContributionScore DESC
        """;
        return entityManager
                .createQuery(jpql, Object[].class)
                .setParameter("groupId", groupId)
                .getResultList();
    }
}
