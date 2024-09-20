package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @JsonSetter("phone")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    private String city;
    @Column(nullable = false)
    private String password;
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;
    private boolean active;
    @Column(name = "activation_code")
    private String activationCode;
}
