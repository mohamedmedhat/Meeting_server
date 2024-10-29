package com.example.demo.user;

import com.example.demo.user.dto.request.LoginInputRequestDto;
import com.example.demo.user.dto.request.RegisterInputRequestDto;
import com.example.demo.user.dto.response.LoginResponseDto;
import com.example.demo.user.dto.response.RegisterResponseDto;

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
