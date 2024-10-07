package com.example.demo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginInputRequestDto {
    @NotBlank(message = "email is mandatory")
    @Email(message = "email should be in correct format")
    private String email;

    @NotBlank(message = "password is a mandatory")
    @Size(min = 6, max = 30)
    private String password;

    public LoginInputRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
