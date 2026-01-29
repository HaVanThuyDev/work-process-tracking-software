package com.example.vn2_ht_student.model.dto;

import com.example.vn2_ht_student.model.enums.Role;
import lombok.Getter;
import lombok.Setter;
import  com.example.vn2_ht_student.model.entity.User;

@Getter
@Setter

public class UserDto {
    private String gmail;
    private String msv;
    private String className;
    private String fullName;
    private String img;
    private Role role;
    public UserDto(User user){
        this.gmail = user.getGmail();
        this.msv = user.getMsv();
        this.fullName = user.getFullName();
        this.img = user.getImg();
        this.role = user.getRole();
        this.className =user.getClassId();
    }
}
