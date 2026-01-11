package com.example.vn2_ht_student.controller;

import com.example.vn2_ht_student.model.SuccessResponse;
import com.example.vn2_ht_student.model.entity.User;
import com.example.vn2_ht_student.model.dto.reponse.LoginReponseDto;
import com.example.vn2_ht_student.model.dto.request.LoginRequestDto;
import com.example.vn2_ht_student.model.dto.request.RegisterRequest;
import com.example.vn2_ht_student.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenController {
    private final UserService userService;

    @PostMapping("/login")
    public SuccessResponse<LoginReponseDto> login (@RequestBody LoginRequestDto request) {
        LoginReponseDto user = userService.login(request.getGmail() , request.getPassword());
        return  new SuccessResponse<>(
                200,
                "Login success",
                user
        );
    }
    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterRequest request) {
        try {
            User user =userService.register(request);
            return ResponseEntity.ok("register success");
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("error"+e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body("register not success");
        }


    }

}
