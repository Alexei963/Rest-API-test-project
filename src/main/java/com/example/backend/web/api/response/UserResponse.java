package com.example.backend.web.api.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String city;
    private LocalDateTime registrationDate;
}
