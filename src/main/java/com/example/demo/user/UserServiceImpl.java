package com.example.demo.user;

import com.example.demo.user.dto.request.LoginInputRequestDto;
import com.example.demo.user.dto.request.RegisterInputRequestDto;
import com.example.demo.user.dto.response.UserResponseDto;
import com.example.demo.user.exceptions.UserNotFoundException;
import com.example.demo.user.dto.response.LoginResponseDto;
import com.example.demo.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public Mono<UserResponseDto> signUp(RegisterInputRequestDto userData) {
        User user = this.userMapper.registerInputRequestToEntity(userData, passwordEncoder);
        return this.userRepository.save(user)
                .map(this.userMapper::toUserResponseDto);
    }

    public Mono<Boolean> validatePassword(String rawPassword, String encodedPassword) {
        return this.passwordEncoder.matches(rawPassword, encodedPassword)
                ? Mono.just(true)
                : Mono.just(false);
    }

    @Override
    public Mono<LoginResponseDto> login(LoginInputRequestDto dto) {
        return Mono.fromCallable(() -> authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())))
                .then(userRepository.findByEmail(dto.email())
                        .switchIfEmpty(Mono.error(new RuntimeException("User not found"))))
                .flatMap(user -> passwordEncoder.matches(dto.password(), user.getPassword())
                        ? Mono.just(user)
                        : Mono.error(new RuntimeException("Invalid credentials")))
                .map(user -> {
                    String token = jwtUtil.generateToken(user);
                    return userMapper.toLoginResponseDto(userMapper.toUserResponseDto(user), token);
                });
    }

    @Override
    public Mono<UserResponseDto> getUserById(String id) {
        return this.userRepository.findById(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException("User not found with id: " + id)))
                .map(this.userMapper::toUserResponseDto);
    }

    @Override
    public Flux<UserResponseDto> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAllBy(pageable)
                .map(userMapper::toUserResponseDto);
    }

    @Override
    public Mono<UserResponseDto> updateUser(String id, RegisterInputRequestDto dto) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException("User not found: " + id)))
                .flatMap(existing -> {
                    userMapper.userUpdateToEntity(dto, existing, passwordEncoder);
                    return userRepository.save(existing);
                })
                .map(userMapper::toUserResponseDto);
    }

    @Override
    public Mono<Void> deleteUser(String id) {
        return userRepository.existsById(id)
                .flatMap(exists -> exists.booleanValue()
                        ? userRepository.deleteById(id)
                        : Mono.error(new UserNotFoundException("User not found: " + id)));
    }

    Mono<Void> checkUserIdExists(String id) {
        if (id == null || id.isEmpty()) {
            return Mono.error(new IllegalArgumentException("User ID cannot be null or empty"));
        }
        return this.userRepository.existsById(id)
                .flatMap(exists -> exists.booleanValue()
                        ? Mono.empty()
                        : Mono.error(new UserNotFoundException("User not found with id: " + id)));
    }
}
