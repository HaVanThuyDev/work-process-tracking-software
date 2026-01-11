package com.example.vn2_ht_student.repository.custome;

import com.example.vn2_ht_student.model.dto.RolePermissionDTO;
import com.example.vn2_ht_student.model.enums.Role;
import java.util.List;

public interface RolePermissionScopeCustomRepository {
    List<RolePermissionDTO> findPermissionsByRole(Role role );
}
