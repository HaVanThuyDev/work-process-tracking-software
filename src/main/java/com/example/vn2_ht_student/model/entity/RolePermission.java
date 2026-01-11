package com.example.vn2_ht_student.model.entity;

import com.example.vn2_ht_student.model.enums.Role;
import com.example.vn2_ht_student.model.enums.Scope;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name ="role_permission_scope")
public class RolePermission {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name ="ROLE_NAME")
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name ="SCOPE")
    private Scope scope;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="PERMISSION_ID")
    private Permission permission;

    @Column(name ="TYPE")
    private String type;
}
