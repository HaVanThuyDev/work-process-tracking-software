package com.example.vn2_ht_student.service;

import com.example.vn2_ht_student.model.dto.CourseDto;
import com.example.vn2_ht_student.model.dto.request.CourseRequestDto;

import java.util.List;

public interface CourseService {
    CourseDto create(CourseRequestDto request);
    CourseDto getById(Long id);
    List<CourseDto> getAll();
    CourseDto update(Long id, CourseRequestDto courseDto);
    CourseDto delete(Long id);


}
