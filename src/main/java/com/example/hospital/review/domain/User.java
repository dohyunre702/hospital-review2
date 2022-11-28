package com.example.hospital.review.domain;

import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String emailAddress;
}
