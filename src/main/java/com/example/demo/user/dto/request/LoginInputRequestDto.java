package com.example.demo.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginInputRequestDto(
        @NotBlank(message = "email is mandatory")
        @Email(message = "email should be in correct format") 
        String email,

        @NotBlank(message = "password is a mandatory") 
        @Size(min = 6, max = 30) 
        String password) {
}
