package com.example.demo.user.dto.response;

import java.util.Set;

public record UserResponseDto(
        String id,
        String name,
        String email,
        Set<String> roles) {
}
