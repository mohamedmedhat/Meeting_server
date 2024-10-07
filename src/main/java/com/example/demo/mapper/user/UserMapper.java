package com.example.demo.mapper.user;

import com.example.demo.dto.request.RegisterInputRequestDto;
import com.example.demo.dto.response.LoginResponseDto;
import com.example.demo.dto.response.RegisterResponseDto;
import com.example.demo.dto.response.UserResponseDto;
import com.example.demo.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UserMapper implements IUserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public RegisterResponseDto toRegisterResponseDto(User user) {
        return new RegisterResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRoles()
        );
    }

    @Override
    public User RegisterInputRequestToEntity(RegisterInputRequestDto dto) {
        String hashPassword = this.encodePassword(dto.getPassword());
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(hashPassword);
        user.setRoles(dto.getRoles());
        return user;
    }

    @Override
    public User UserUpdateTOEntity(RegisterInputRequestDto dto, User user) {
        String hashPassword = this.encodePassword(dto.getPassword());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(hashPassword);
        user.setRoles(dto.getRoles());
        return user;
    }

    @Override
    public UserResponseDto toUserResponseDto(User user) {
        return new UserResponseDto(
                user.getName(),
                user.getEmail(),
                user.getRoles()
        );
    }

    @Override
    public LoginResponseDto toLoginResponseDto(UserResponseDto user, String token) {
        return new LoginResponseDto(
                user,
                token
        );
    }
}
