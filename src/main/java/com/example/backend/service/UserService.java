package com.example.backend.service;

import com.example.backend.web.api.request.UserRequest;
import com.example.backend.web.api.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse addUser(UserRequest userRequest);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(int id);
    UserResponse updateUser(int id, UserRequest userRequest);
    void deleteUser(int id);
}
