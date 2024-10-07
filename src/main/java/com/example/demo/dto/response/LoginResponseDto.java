package com.example.demo.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDto {
    private UserResponseDto user;
    private String token;

    public LoginResponseDto(UserResponseDto user, String token) {
        this.user = user;
        this.token = token;
    }
}
