package com.example.vn2_ht_student.model.dto.reponse;

import com.example.vn2_ht_student.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class LoginReponseDto {
    private String username;
    private Role role;
    private String token;


}
