package com.example.vn2_ht_student.model.entity;

import com.example.vn2_ht_student.model.enums.Role;
import com.example.vn2_ht_student.model.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "GMAIL", nullable = false, unique = true, length = 190)
    private String gmail;

    @Column(name = "MSV", nullable = false)
    private String msv;

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "IMG")
    private String img;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;
}
