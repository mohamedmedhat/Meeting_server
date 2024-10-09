package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class RegisterResponseDto {
    private Long id;
    private String name;
    private String email;
    protected Set<String> roles;
}
