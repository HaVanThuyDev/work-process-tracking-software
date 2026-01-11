package com.example.vn2_ht_student.exeption;

import lombok.Getter;

@Getter
public class QueryValidationException extends RuntimeException {// thông báo trước khi chạy chương trình
    public QueryValidationException(String message) {
        super(message);
    }
    public QueryValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
