package com.example.vn2_ht_student.service.Impl;

import com.example.vn2_ht_student.model.dto.CourseDto;
import com.example.vn2_ht_student.model.dto.request.CourseRequestDto;
import com.example.vn2_ht_student.model.entity.Course;
import com.example.vn2_ht_student.model.enums.Action;
import com.example.vn2_ht_student.model.enums.ResourceType;
import com.example.vn2_ht_student.model.enums.Role;
import com.example.vn2_ht_student.repository.CourseRepository;
import com.example.vn2_ht_student.security.SecurityUtils;
import com.example.vn2_ht_student.security.annotation.PermissionCheck;
import com.example.vn2_ht_student.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    @PermissionCheck(roles = { Role.LECTURER }, resource = ResourceType.COURSE, action = Action.COURSE_CREATE)
    public CourseDto create(CourseRequestDto request) {
        Long userId = SecurityUtils.getCurrentUserId();
        if (userId == null) {
            throw new AccessDeniedException("Unauthenticated");
        }
        Course course =new Course();
        course.setCourseName(request.getCourseName());
        course.setSemester(request.getSemester());
        course.setCreatedAt(LocalDateTime.from(request.getCreatedAt()));
        course.setCreatedBy(String.valueOf(userId));
        course.setExpiration(LocalDateTime.from(request.getExpiration()));
        Course savedCourse = courseRepository.save(course);
        return new CourseDto(savedCourse);
    }

    @Override
    public CourseDto getById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(()->new RuntimeException("Course Not Found"));
        return new CourseDto(course);
    }

    @Override
    public List<CourseDto> getAll() {
        return List.of();
    }

    @Override
    public CourseDto update(Long id, CourseRequestDto courseDto) {
        return null;
    }

    @Override
    public CourseDto delete(Long id) {
        return null;
    }
}
