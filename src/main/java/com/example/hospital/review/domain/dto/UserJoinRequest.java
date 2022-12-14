package com.example.hospital.review.domain.dto;

import com.example.hospital.review.domain.User;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserJoinRequest {
    private String userName;
    private String password;
    private String emailAddress;

    public User toEntity(String password) {
        return User.builder()
                .userName(this.userName)
                .password(password)
                .emailAddress(this.emailAddress)
                .build();
    }
}
