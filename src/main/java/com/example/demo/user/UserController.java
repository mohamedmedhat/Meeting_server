package com.example.demo.user;

import com.example.demo.user.dto.request.LoginInputRequestDto;
import com.example.demo.user.dto.request.RegisterInputRequestDto;
import com.example.demo.user.dto.response.LoginResponseDto;
import com.example.demo.user.dto.response.UserResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users/")
public class UserController {
    private final UserService userService;

    @PostMapping("auth/register")
    public Mono<ResponseEntity<UserResponseDto>> register(@Valid @RequestBody RegisterInputRequestDto userData) {
        return this.userService.signUp(userData)
                .map(userResponseDto -> ResponseEntity.status(201).body(userResponseDto));
    }

    @PostMapping("auth/login")
    public Mono<ResponseEntity<LoginResponseDto>> login(@Valid @RequestBody LoginInputRequestDto userData) {
        return this.userService.login(userData)
                .map(ResponseEntity::ok);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<UserResponseDto>> updateUser(@PathVariable("id") String id,
            @Valid @RequestBody RegisterInputRequestDto userData) {
        return this.userService.updateUser(id, userData)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable("id") String id) {
        this.userService.deleteUser(id);
        return Mono.just(ResponseEntity.noContent().build());
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ORGANIZER')")
    public Mono<ResponseEntity<UserResponseDto>> getOne(@PathVariable("id") String id) {
        return this.userService.getUserById(id)
                .map(ResponseEntity::ok);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Flux<ResponseEntity<UserResponseDto>> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return this.userService.getAllUsers(page, size)
                .map(ResponseEntity::ok);
    }
}
