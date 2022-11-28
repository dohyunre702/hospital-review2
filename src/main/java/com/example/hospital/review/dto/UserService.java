package com.example.hospital.review.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserService {
    public UserDto join(UserJoinRequest userJoinRequest) {
        return new UserDto();
    }
}
