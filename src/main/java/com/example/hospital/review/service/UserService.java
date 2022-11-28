package com.example.hospital.review.service;

import com.example.hospital.review.domain.User;
import com.example.hospital.review.domain.dto.UserDto;
import com.example.hospital.review.domain.dto.UserJoinRequest;
import com.example.hospital.review.repository.UserRepository;
import lombok.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
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
                    throw new RuntimeException("해당 UserName이 중복됩니다.");
                });

        //회원가입 .save()
        User savedUser = userRepository.save(request.toEntity());
        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .emailAddress(savedUser.getEmailAddress())
                .build();
    }
}
