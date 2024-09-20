package com.example.backend.web.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {

//    private int id;
    @NotBlank(message = "Поле имя не должно быть пустым!")
    private String name;
    @Email(message = "Неверный формат email!")
    private String email;
    private String phone;
    @Size(min = 8, message = "Минимальное количество символов пароле {min}!")
    private String password;
    private String city;
}
