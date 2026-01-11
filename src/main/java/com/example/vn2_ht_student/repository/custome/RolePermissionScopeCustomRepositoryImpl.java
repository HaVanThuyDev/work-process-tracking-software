package com.example.vn2_ht_student.repository.custome;
import com.example.vn2_ht_student.model.dto.RolePermissionDTO;
import com.example.vn2_ht_student.model.enums.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class RolePermissionScopeCustomRepositoryImpl implements RolePermissionScopeCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RolePermissionDTO> findPermissionsByRole(Role role) {

        String jpql = """
        SELECT new com.example.vn2_ht_student.model.dto.RolePermissionDTO(
            p.resource,
            p.action,
            db.scope
        )
        FROM RolePermission db
        JOIN db.permission p
        WHERE db.role = :role
    """;
        return entityManager
                .createQuery(jpql, RolePermissionDTO.class)
                .setParameter("role", role)
                .getResultList();
    }

}