package com.example.demo.dto.response;

import lombok.Data;

import java.util.Set;

@Data
public class UserResponseDto {
    private String name;
    private String email;
    protected Set<String> roles;

    public UserResponseDto(String name, String email, Set<String> roles) {
        this.name = name;
        this.email = email;
        this.roles = roles;
    }
}
