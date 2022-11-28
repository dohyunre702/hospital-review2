package com.example.hospital.review.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "User name is duplicated.");
    //.mR
    private HttpStatus status;
    private String message;
}
