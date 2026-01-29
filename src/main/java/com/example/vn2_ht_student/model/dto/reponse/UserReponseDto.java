package com.example.vn2_ht_student.model.dto.reponse;

import com.example.vn2_ht_student.model.entity.User;
import com.example.vn2_ht_student.model.enums.Role;
import com.example.vn2_ht_student.model.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReponseDto {
    private String gmail;
    private String msv;
    private String fullName;
    private Role role;
    private Status status;
    public UserReponseDto(User user) {
        this.gmail =user.getGmail();
        this.msv = user.getMsv();
        this.fullName = user.getFullName();
        this.role = user.getRole();
        this.status = user.getStatus();
    }
}
