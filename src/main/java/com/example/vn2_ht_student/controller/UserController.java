package com.example.vn2_ht_student.controller;


import com.example.vn2_ht_student.Utils.Constants;
import com.example.vn2_ht_student.model.dto.UserDto;
import com.example.vn2_ht_student.model.dto.reponse.ResponseDTO;
import com.example.vn2_ht_student.model.dto.reponse.UserReponseDto;
import com.example.vn2_ht_student.model.dto.request.UserRequestDto;
import com.example.vn2_ht_student.model.entity.User;
import com.example.vn2_ht_student.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> getAllUsers() {

        List<UserDto> users = userService.getAllUsers();

        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .status("ok")
                        .code(Constants.HTTP_STATUS.SUCCESS)
                        .data(users)
                        .message("Lấy danh sách tài khoản thành công")
                        .build()
        );
    }

    @PostMapping("/upate/{id}")
    public ResponseEntity<?> update (@PathVariable Long id, @RequestBody UserRequestDto request){
        try {
            userService.update(id, request);
            return ResponseEntity.ok(ResponseDTO.builder()
                    .status("ok")
                    .code(Constants.HTTP_STATUS.SUCCESS)
                    .message("cập nhật tài khoản thành công")
                    .build());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDTO.builder()
                    .status("error")
                    .code(Constants.HTTP_STATUS.INTERNAL_SERVER_ERROR)
                    .data(null)
                    .message(e.getMessage())
                    .build());
        }
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity <?> delete (@PathVariable Long id){
        try {
            userService.delete(id);
            return ResponseEntity.ok(ResponseDTO.builder()
                    .status("ok")
                    .code(Constants.HTTP_STATUS.SUCCESS)
                    .message("xóa tài khoản thành công")
                    .build());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDTO.builder()
                    .status("error")
                    .code(Constants.HTTP_STATUS.INTERNAL_SERVER_ERROR)
                    .data(null)
                    .message(e.getMessage())
                    .build());
        }
    }
    @GetMapping("/search/")
    public  ResponseEntity<ResponseDTO<Object>> search (@RequestParam(required = false) String fullName, @RequestParam(required = false) String msv) {
        User uer = (User) userService.search(fullName,msv);
        return ResponseEntity.ok(ResponseDTO.builder()
                .status("oke")
                .code(Constants.HTTP_STATUS.SUCCESS)
                .message("tìm kiếm thành công")
                .data(uer)
                .build()
        );
    }
    @PostMapping("/details/{id}")
    public ResponseEntity<?> details (@PathVariable Long id){
        UserReponseDto userReponseDto =userService.GetDetail(id);
        return ResponseEntity.ok(ResponseDTO.builder()
                .status("oke")
                .code(Constants.HTTP_STATUS.SUCCESS)
                .message("lấy danh sách details thành công")
                .data(userReponseDto)
                .build()
        );
    }
    @PostMapping("/import")
    public ResponseEntity<?> importUsersFromExcel( @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        if (!file.getOriginalFilename().endsWith(".xlsx")) {
            return ResponseEntity.badRequest().body("Only .xlsx file is allowed");
        }
        try {
            userService.importUsersFromExcel(file);
            return ResponseEntity.ok("IMPORT USERS SUCCESS");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("IMPORT FAILED: " + e.getMessage());
        }
    }

}
