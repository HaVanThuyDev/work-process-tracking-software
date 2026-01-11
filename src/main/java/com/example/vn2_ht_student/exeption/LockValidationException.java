package com.example.vn2_ht_student.exeption;

public class LockValidationException extends RuntimeException {
    private Object details;
    public LockValidationException(String message) {
        super(message);
    }
    public LockValidationException(String message, Object details) {
        super(message);
        this.details = details;
    }
    public Object getDetails() {
        return details;
    }
}
