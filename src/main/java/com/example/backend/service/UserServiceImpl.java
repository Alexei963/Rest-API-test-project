package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.repository.UserRepository;
import com.example.backend.web.api.request.UserRequest;
import com.example.backend.web.api.response.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        User user = objectMapper.convertValue(userRequest, User.class);
        user.setRegistrationDate(LocalDateTime.now());
        userRepository.save(user);
        log.info("Пользователь {} создан!", user.getName());
        return objectMapper.convertValue(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .map(user -> objectMapper.convertValue(user, UserResponse.class))
                .toList();
    }

    private User getUser(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageFormat
                        .format("Пользователь с id: {0} не найден!", id)));
    }

    @Override
    public UserResponse getUserById(int id) {
        User user = getUser(id);
        return objectMapper.convertValue(user, UserResponse.class);
    }

    @Override
    public UserResponse updateUser(int id, UserRequest userRequest) {
        User user = getUser(id);
        if (!user.getName().equals(userRequest.getName()) && userRequest.getName() != null) {
            log.info("Пользователь {} изменил имя на {}!", user.getName(), userRequest.getName());
            user.setName(userRequest.getName());
        }
        if (!user.getEmail().equals(userRequest.getEmail()) && userRequest.getEmail() != null) {
            log.info("Пользователь {} изменил email на {}!", user.getName(), userRequest.getEmail());
            user.setEmail(userRequest.getEmail());
        }
        if (!user.getPhoneNumber().equals(userRequest.getPhone()) && userRequest.getPhone() != null) {
            log.info("Пользователь {} изменил номер телефона на {}!", user.getName(), userRequest.getPhone());
            user.setPhoneNumber(userRequest.getPhone());
        }
        if (!user.getPassword().equals(userRequest.getPassword()) && userRequest.getPassword() != null) {
            log.info("Пользователь {} изменил пароль!", user.getName());
            user.setPassword(userRequest.getPassword());
        }
        if (user.getCity() == null || !user.getCity().equals(userRequest.getCity()) && userRequest.getCity() != null) {
            log.info("Пользователь {} изменил город на {}!", user.getName(), userRequest.getCity());
            user.setCity(userRequest.getCity());
        }
        userRepository.save(user);
        return objectMapper.convertValue(user, UserResponse.class);
    }

    @Override
    public void deleteUser(int id) {
        User user = getUser(id);
        log.info("Пользователь {} удалён!", user.getName());
        userRepository.delete(user);
    }
}
