package com.example.vn2_ht_student.model.dto;

import com.example.vn2_ht_student.model.enums.Action;
import com.example.vn2_ht_student.model.enums.Scope;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RolePermissionDTO {
    private String resource;
    private Action action;
    private Scope scope;
}