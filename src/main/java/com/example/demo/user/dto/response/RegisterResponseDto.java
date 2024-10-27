package com.example.demo.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class RegisterResponseDto {
    private String id;
    private String name;
    private String email;
    protected Set<String> roles;
}
