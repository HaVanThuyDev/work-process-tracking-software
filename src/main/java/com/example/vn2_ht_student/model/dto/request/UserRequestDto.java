package com.example.vn2_ht_student.model.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserRequestDto {
    private String gmail;
    private String msv;
    private String fullName;
    private String password;
    private String img;

}
