package com.example.hospital.review.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserJoinRequest {
    private String userName;
    private String password;
    private String emailAddress;
}
