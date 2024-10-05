package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterInputRequestDto {
    @NotBlank(message = "name is mandatory")
    @Size(min = 10, max = 40, message = "name should have at least 10 characters and maximum 40 characters")
    private String name;

    @NotBlank(message = "email is mandatory")
    @Email(message = "email should be in correct format")
    private String email;

    @NotBlank(message = "password is mandatory")
    @Size(min = 6, max = 30, message = "password length should be least 6 and maximum 40 ")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
