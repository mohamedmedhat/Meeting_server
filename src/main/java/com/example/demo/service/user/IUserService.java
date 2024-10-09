package com.example.demo.service.user;

import com.example.demo.dto.request.LoginInputRequestDto;
import com.example.demo.dto.request.RegisterInputRequestDto;
import com.example.demo.dto.response.LoginResponseDto;
import com.example.demo.model.User;
import com.example.demo.dto.response.RegisterResponseDto;

import java.util.List;

public interface IUserService {
    RegisterResponseDto signUp(RegisterInputRequestDto userData);

    Boolean validatePassword(String rawPassword, String encodedPassword);

    LoginResponseDto login(LoginInputRequestDto userData);

    RegisterResponseDto updateUser(Long id, RegisterInputRequestDto userData);

    User getUserById(Long id);

    List<User> getAllUsers();

    Boolean deleteUser(Long id);
}
