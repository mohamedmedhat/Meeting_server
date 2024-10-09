package com.example.demo.controller;

import com.example.demo.dto.request.LoginInputRequestDto;
import com.example.demo.dto.request.RegisterInputRequestDto;
import com.example.demo.dto.response.RegisterResponseDto;
import com.example.demo.model.User;
import com.example.demo.dto.response.LoginResponseDto;
import com.example.demo.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("auth/register")
    public RegisterResponseDto register(@Valid @RequestBody RegisterInputRequestDto userData) {
        return this.userService.signUp(userData);
    }

    @PostMapping("auth/login")
    public LoginResponseDto login(@Valid @RequestBody LoginInputRequestDto userData) {
        return this.userService.login(userData);
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RegisterResponseDto updateUser(@PathVariable("id") Long id, @Valid @RequestBody RegisterInputRequestDto userData) {
        return this.userService.updateUser(id, userData);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean deleteUser(@PathVariable("id") Long id) {
        return this.userService.deleteUser(id);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ORGANIZER')")
    public User getOne(@PathVariable("id") Long id) {
        return this.userService.getUserById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAll() {
        return this.userService.getAllUsers();
    }
}
