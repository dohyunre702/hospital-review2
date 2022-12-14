package com.example.hospital.review.controller;

import com.example.hospital.review.domain.dto.UserDto;
import com.example.hospital.review.domain.dto.UserJoinRequest;
import com.example.hospital.review.exception.ErrorCode;
import com.example.hospital.review.exception.HospitalReviewAppException;
import com.example.hospital.review.service.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



//Service에서 에러가 나지 않으면 ok, 에러가 발생하면 conflict를 체크하는 테스트
@WebMvcTest
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    @DisplayName("회원가입 성공")
    @WithMockUser
    void join_success() throws Exception {
        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName("dd")
                .password("1q2w3e4r")
                .emailAddress("ddhlee@gamil.com")
                .build();

        when(userService.join(any())).thenReturn(mock(UserDto.class));

        mockMvc.perform(post("/api.v1/users/join")
                        .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)

                .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 실패")
    void join_fail() throws Exception {

        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName("dd")
                .password("1q2w3e4r")
                .emailAddress("ddhlee@gamil.com")
                .build();

        when(userService.join(any())).thenThrow(new HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME, ""));

        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)

                .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                .andDo(print())
                .andExpect(status().isConflict());
        }

    @Test
    @DisplayName("로그인 실패 - id 없음")
    void login_fail1() throws Exception {
        //String id = "dd"
        //String password = "1q2w3e4r"

        //id, pw 보내기
        when(userService.login(any(), any())).thenThrow(new HospitalReviewAppException(ErrorCode.NOT_FOUND, ""));

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)

                        .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
    }

