package com.example.vn2_ht_student.model.entity;

import com.example.vn2_ht_student.model.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Entity
@Setter
@Table(name = "student_groups")
public class Group extends BaseModifiedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "GROUP_NAME", nullable = false)
    private String groupName;

    @ManyToOne
    @JoinColumn(name = "COURSE_ID")
    private Course course;

    @Column(name = "MODIFIED_AT")
    private LocalDateTime modifiedAt;


}
