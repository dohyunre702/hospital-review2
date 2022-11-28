package com.example.hospital.review.domain;

import lombok.*;

@AllArgsConstructor
@Getter
public class Response<T> {
    private String resultCode;
    private T result;

    public static Response<Void> error(String resultCode) {
        return new Response(resultCode, null);
    }

    public static <T> Response<T> success(T result) {
        return new Response("SUCCESS", null);
    }
}
