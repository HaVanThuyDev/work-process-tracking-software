package com.example.vn2_ht_student.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class SuccessResponse<T> {
    private int satus;
    private String message;
    private  T data;
}
