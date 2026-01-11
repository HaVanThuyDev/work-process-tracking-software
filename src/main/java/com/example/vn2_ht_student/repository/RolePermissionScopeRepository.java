package com.example.vn2_ht_student.repository;

import com.example.vn2_ht_student.model.entity.RolePermission;
import com.example.vn2_ht_student.repository.custome.RolePermissionScopeCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionScopeRepository extends JpaRepository<RolePermission, Long>, RolePermissionScopeCustomRepository {
}
