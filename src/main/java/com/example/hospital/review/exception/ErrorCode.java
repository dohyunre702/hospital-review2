package com.example.hospital.review.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "Username is duplicated."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "Username Not Found."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "");
    private HttpStatus status;
    private String message;
}
