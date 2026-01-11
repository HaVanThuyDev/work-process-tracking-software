package com.example.vn2_ht_student.model.enums;

import lombok.Getter;

public enum TaskStatus {

    TODO("Chưa làm"),
    DOING("Đang làm"),
    DONE("Hoàn thành");

    @Getter
    private final String description;

    TaskStatus(String description) {
        this.description = description;
    }
}
