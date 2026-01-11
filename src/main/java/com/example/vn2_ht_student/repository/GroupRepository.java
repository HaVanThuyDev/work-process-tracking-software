package com.example.vn2_ht_student.repository;

import com.example.vn2_ht_student.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByCourseId(Long courseId);


}
