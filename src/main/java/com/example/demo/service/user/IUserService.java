package com.example.demo.service.user;

import com.example.demo.dto.request.LoginInputRequestDto;
import com.example.demo.dto.request.RegisterInputRequestDto;
import com.example.demo.dto.response.LoginResponseDto;
import com.example.demo.model.User;
import com.example.demo.dto.response.RegisterResponseDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IUserService {
    CompletableFuture<RegisterResponseDto> signUp(RegisterInputRequestDto userData);

    CompletableFuture<Boolean> validatePassword(String rawPassword, String encodedPassword);

    CompletableFuture<LoginResponseDto> login(LoginInputRequestDto userData);

    CompletableFuture<RegisterResponseDto> updateUser(String id, RegisterInputRequestDto userData);

    User getUserById(String id);

    List<User> getAllUsers();

    Boolean deleteUser(String id);
}
