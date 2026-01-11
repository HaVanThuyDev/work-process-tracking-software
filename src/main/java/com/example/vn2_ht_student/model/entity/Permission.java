package com.example.vn2_ht_student.model.entity;

import com.example.vn2_ht_student.model.enums.Action;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name ="dim_permission")

public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="RESOURCE")
    private String resource;
    @Enumerated(EnumType.STRING)
    @Column(name ="ACTION")
    private Action action;

}
