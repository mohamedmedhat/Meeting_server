package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginInputRequestDto {
    @NotBlank(message = "email is mandatory")
    @Email(message = "email should be in correct format")
    private String email;

    @NotBlank(message = "password is a mandatory")
    @Size(min = 6, max = 30)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
