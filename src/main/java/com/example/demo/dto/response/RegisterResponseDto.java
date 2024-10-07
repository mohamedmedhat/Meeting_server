package com.example.demo.dto.response;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterResponseDto {
    private Long id;
    private String name;
    private String email;
    protected Set<String> roles;

    public RegisterResponseDto(Long id, String name, String email, Set<String> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }
}
