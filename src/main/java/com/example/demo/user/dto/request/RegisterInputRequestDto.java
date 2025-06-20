package com.example.demo.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public record RegisterInputRequestDto(
        @NotBlank(message = "name is mandatory")
        @Size(min = 10, max = 40, message = "name should have at least 10 characters and maximum 40 characters")
        String name,

        @NotBlank(message = "email is mandatory")
        @Email(message = "email should be in correct format")
        String email,

        @NotBlank(message = "password is mandatory")
        @Size(min = 6, max = 30, message = "password length should be least 6 and maximum 40 ")
        String password,

        Set<String> roles) {    

    public RegisterInputRequestDto(String name, String email, String password, Set<String> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = (roles != null && !roles.isEmpty()) ? roles : new HashSet<>(Collections.singleton("USER"));
    }
}
