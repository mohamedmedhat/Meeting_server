package com.example.demo.mapper.user;

import com.example.demo.dto.request.RegisterInputRequestDto;
import com.example.demo.dto.response.LoginResponseDto;
import com.example.demo.dto.response.RegisterResponseDto;
import com.example.demo.dto.response.UserResponseDto;
import com.example.demo.model.User;

public interface IUserMapper {
    String encodePassword(String password);

    RegisterResponseDto toRegisterResponseDto(User user);

    User RegisterInputRequestToEntity(RegisterInputRequestDto dto);

    User UserUpdateTOEntity(RegisterInputRequestDto dto, User user);

    UserResponseDto toUserResponseDto(User user);

    LoginResponseDto toLoginResponseDto(UserResponseDto user, String token);
}
