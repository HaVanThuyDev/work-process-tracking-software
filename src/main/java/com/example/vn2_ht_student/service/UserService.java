package com.example.vn2_ht_student.service;

import com.example.vn2_ht_student.model.dto.UserDto;
import com.example.vn2_ht_student.model.entity.User;
import com.example.vn2_ht_student.model.dto.reponse.LoginReponseDto;
import com.example.vn2_ht_student.model.dto.reponse.UserReponseDto;
import com.example.vn2_ht_student.model.dto.request.RegisterRequest;
import com.example.vn2_ht_student.model.dto.request.UserRequestDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {
    LoginReponseDto login(String gmail, String passwords );
    User register(RegisterRequest request);
    UserDto update(Long id, UserRequestDto userRequestDto);
    List<UserReponseDto> getAllUsers();
    List<UserReponseDto> delete (Long id);
    List<UserReponseDto> search(String fullName, String msv);
    UserReponseDto GetDetail(Long id);

}
