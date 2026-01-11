package com.example.vn2_ht_student.repository;

import com.example.vn2_ht_student.model.entity.Permission;
import com.example.vn2_ht_student.model.enums.Action;
import com.example.vn2_ht_student.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query("""
        SELECT p.action
        FROM RolePermission rp
        JOIN rp.permission p
        WHERE rp.role = :role
          AND p.resource = :resource
    """)
    List<Action> findActionsByRoleAndResource(
            @Param("role") Role role,
            @Param("resource") String resource
    );
}
