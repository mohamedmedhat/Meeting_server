package com.example.demo.service.user;

import com.example.demo.dto.request.LoginInputRequestDto;
import com.example.demo.dto.request.RegisterInputRequestDto;
import com.example.demo.dto.response.UserResponseDto;
import com.example.demo.mapper.user.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.dto.response.LoginResponseDto;
import com.example.demo.dto.response.RegisterResponseDto;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailService userDetailService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserService(
            UserRepository userRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil,
            AuthenticationManager authenticationManager,
            UserDetailService userDetailService
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
    }

    @Override
    public RegisterResponseDto signUp(RegisterInputRequestDto userData) {
        try {
            User user = this.userMapper.RegisterInputRequestToEntity(userData);
            User savedUser = this.userRepository.save(user);
            return this.userMapper.toRegisterResponseDto(savedUser);
        } catch (Exception e) {
            throw new RuntimeException("Error during sign-up: " + e.getMessage(), e);
        }
    }

    @Override
    public Boolean validatePassword(String rawPassword, String encodedPassword) {
        try {
            return this.passwordEncoder.matches(rawPassword, encodedPassword);
        } catch (Exception e) {
            throw new RuntimeException("Error during password validation: " + e.getMessage(), e);
        }
    }

    @Override
    public LoginResponseDto login(LoginInputRequestDto userData) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userData.getEmail(), userData.getPassword()));
        User user = this.userRepository.findByEmail(userData.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (this.validatePassword(userData.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user);
            UserResponseDto userDto = this.userMapper.toUserResponseDto(user);
            return this.userMapper.toLoginResponseDto(userDto, token);
        }
        throw new RuntimeException("Invalid credentials");
    }


    @Override
    public User getUserById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return this.userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all users: " + e.getMessage(), e);
        }
    }

    @Override
    public RegisterResponseDto updateUser(Long id, RegisterInputRequestDto userData) {
        try {
            User user = this.getUserById(id);
            User updateUser = this.userMapper.UserUpdateTOEntity(userData, user);
            User savedUser = this.userRepository.save(updateUser);
            return this.userMapper.toRegisterResponseDto(savedUser);
        } catch (Exception e) {
            throw new RuntimeException("Error during user update: " + e.getMessage(), e);
        }
    }

    @Override
    public Boolean deleteUser(Long id) {
        try {
            User user = this.getUserById(id);
            this.userRepository.delete(user);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error during user deletion: " + e.getMessage(), e);
        }
    }
}
