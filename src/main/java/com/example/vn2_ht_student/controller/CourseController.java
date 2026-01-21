package com.example.vn2_ht_student.controller;


import com.example.vn2_ht_student.Utils.Constants;
import com.example.vn2_ht_student.model.dto.CourseDto;
import com.example.vn2_ht_student.model.dto.reponse.ResponseDTO;
import com.example.vn2_ht_student.model.dto.reponse.UserReponseDto;
import com.example.vn2_ht_student.model.dto.request.CourseRequestDto;
import com.example.vn2_ht_student.model.entity.Course;
import com.example.vn2_ht_student.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    @PostMapping("/create")
    public ResponseEntity<?> create (@RequestBody CourseRequestDto request) {
        try {
           courseService.create(request);
           return ResponseEntity.ok(ResponseDTO.builder()
                   .status("ok")
                   .code(Constants.HTTP_STATUS.SUCCESS)
                   .message("Thêm khóa học thành công ")
                   .build()
           );
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

}
