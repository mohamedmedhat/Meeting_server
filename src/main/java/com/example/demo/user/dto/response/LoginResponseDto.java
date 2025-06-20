package com.example.demo.user.dto.response;

public record LoginResponseDto(
        UserResponseDto user,
        String token) {
}
