package com.example.demo.user;

import com.example.demo.user.dto.request.RegisterInputRequestDto;
import com.example.demo.user.dto.response.LoginResponseDto;
import com.example.demo.user.dto.response.UserResponseDto;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = { Collectors.class })
public interface UserMapper {
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(dto.password()))")
    @Mapping(target = "roles",
        expression = "java(dto.roles().stream().map(String::toUpperCase).collect(Collectors.toSet()))")
    User registerInputRequestToEntity(RegisterInputRequestDto dto, @Context PasswordEncoder passwordEncoder);

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(dto.password()))")
    @Mapping(target = "roles",
        expression = "java(dto.roles().stream().map(String::toUpperCase).collect(Collectors.toSet()))")
    User userUpdateToEntity(RegisterInputRequestDto dto, @MappingTarget User user, @Context PasswordEncoder passwordEncoder);

    UserResponseDto toUserResponseDto(User user);
    default LoginResponseDto toLoginResponseDto(UserResponseDto user, String token) {
        return new LoginResponseDto(user, token);
    }
}
