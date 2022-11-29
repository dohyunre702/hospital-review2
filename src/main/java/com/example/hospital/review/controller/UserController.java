package com.example.hospital.review.controller;

import com.example.hospital.review.domain.Response;
import com.example.hospital.review.domain.dto.*;
import com.example.hospital.review.service.UserService;

import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public Response<UserJoinResponse> join (@RequestBody UserJoinRequest userJoinRequest) {
        UserDto userDto = userService.join(userJoinRequest);
        return Response.success(new UserJoinResponse(userDto.getUserName(), userDto.getEmailAddress()));
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login (@RequestBody UserLoginRequest userLoginRequest) {
        String token = userService.login(userLoginRequest.getUserName(), userLoginRequest.getPassword());
        return Response.success(new UserLoginResponse(token));
    }

}
