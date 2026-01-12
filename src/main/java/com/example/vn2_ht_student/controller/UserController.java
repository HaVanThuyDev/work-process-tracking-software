package com.example.vn2_ht_student.controller;


import com.example.vn2_ht_student.model.SuccessResponse;
import com.example.vn2_ht_student.model.dto.reponse.UserReponseDto;
import com.example.vn2_ht_student.model.dto.request.UserRequestDto;
import com.example.vn2_ht_student.model.entity.User;
import com.example.vn2_ht_student.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/list")
    public SuccessResponse<List<UserReponseDto>> getAllUsers (){
        return new SuccessResponse<>(
                200,
                "success",
                userService.getAllUsers()
        );
    }
    @PostMapping("/upate/{id}")
    public ResponseEntity<?> update (@PathVariable Long id, @RequestBody UserRequestDto request){
        try {
            userService.update(id, request);
            return ResponseEntity.ok("update Employee success");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity <?> delete (@PathVariable Long id){
        try {
            userService.delete(id);
            return ResponseEntity.ok("delete success");
        }catch (IllegalAccessError e){
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/search/")
    public  SuccessResponse<User> search (@RequestParam(required = false) String fullName, @RequestParam(required = false) String msv) {
        User uer = (User) userService.search(fullName,msv);
        return  new SuccessResponse<>(
                200,
                " search success",
                uer
        );
    }
    @PostMapping("/details/{id}")
    public ResponseEntity<?> details (@PathVariable Long id){
        UserReponseDto userReponseDto =userService.GetDetail(id);
        return ResponseEntity.ok(userReponseDto);
    }
}
