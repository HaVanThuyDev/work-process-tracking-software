package com.example.vn2_ht_student.model.dto.reponse;

import com.example.vn2_ht_student.model.entity.User;
import com.example.vn2_ht_student.model.enums.Role;
import com.example.vn2_ht_student.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReponseDto {
    private String msv;
    private String fullName;
    private Role role;
    private Status status;
    public UserReponseDto(User user) {
        this.msv = msv;
        this.fullName = fullName;
        this.role = role;
        this.status = status;
    }
}
