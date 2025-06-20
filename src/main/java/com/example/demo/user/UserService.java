package com.example.demo.user;

import com.example.demo.user.dto.request.LoginInputRequestDto;
import com.example.demo.user.dto.request.RegisterInputRequestDto;
import com.example.demo.user.dto.response.LoginResponseDto;
import com.example.demo.user.dto.response.UserResponseDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserResponseDto> signUp(RegisterInputRequestDto userData);

    Mono<LoginResponseDto> login(LoginInputRequestDto userData);

    Mono<UserResponseDto> updateUser(String id, RegisterInputRequestDto userData);

    Mono<UserResponseDto> getUserById(String id);

    Flux<UserResponseDto> getAllUsers(int page, int size);

    Mono<Void> deleteUser(String id);
}
