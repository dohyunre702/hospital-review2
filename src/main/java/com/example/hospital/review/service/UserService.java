package com.example.hospital.review.service;

import com.example.hospital.review.domain.User;
import com.example.hospital.review.domain.dto.UserDto;
import com.example.hospital.review.domain.dto.UserJoinRequest;
import com.example.hospital.review.exception.ErrorCode;
import com.example.hospital.review.exception.HospitalReviewAppException;
import com.example.hospital.review.repository.UserRepository;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    public UserDto join(UserJoinRequest request) {
        // 비즈니스 로직 - 회원 가입
        // 회원 userName(id) 중복 check
        // 중복이면 회원가입 x -> Exception(예외) 발생
        /*
        userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new RuntimeException("해당 UserName이 중복됩니다."));
         */

        // 있으면 에러처리
        userRepository.findByUserName(request.getUserName())
                .ifPresent(user -> {
                    throw new HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME, String.format("UserName:%s", request.getUserName()));
                });

        //회원가입 .save()
        User savedUser = userRepository.save(request.toEntity(encoder.encode(request.getPassword())));
        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .emailAddress(savedUser.getEmailAddress())
                .build();
    }

    public String login(String userName, String password) {
        //userName 있는지 여부 확인
        //없으면 NOT FOUND 에러 발생
        userRepository.findByUserName(userName)
                .orElseThrow(() -> new HospitalReviewAppException(ErrorCode.NOT_FOUND, String.format("%는 가입된 적이 없습니다.", userName)));
        //password 일치여부 확인
        //2가지 확인 중 예외 없으면 Token 발행
        return "";
    }
}
