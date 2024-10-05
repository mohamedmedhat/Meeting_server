package com.example.demo.controller;

import com.example.demo.dto.LoginInputRequestDto;
import com.example.demo.dto.RegisterInputRequestDto;
import com.example.demo.model.UserModel;
import com.example.demo.responses.LoginResponse;
import com.example.demo.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/users/v1")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public CompletableFuture<UserModel> register(@Valid @RequestBody RegisterInputRequestDto userData) {
        return this.userService.signUp(userData);
    }

    @PostMapping("/login")
    public CompletableFuture<LoginResponse> login(@Valid @RequestBody LoginInputRequestDto userData) {
        return this.userService.login(userData);
    }

    @PutMapping("/update/{id}")
    public CompletableFuture<UserModel> updateUser(@PathVariable("id") Long id, @Valid @RequestBody RegisterInputRequestDto userData) {
        return this.userService.updateUser(id, userData);
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<Boolean> deleteUser(@PathVariable("id") Long id) {
        return this.userService.deleteUser(id);
    }

    @GetMapping("/getone/{id}")
    public CompletableFuture<UserModel> getOne(@PathVariable("id") Long id) {
        return this.userService.getUserById(id);
    }

    @GetMapping("/getAll")
    public CompletableFuture<List<UserModel>> getAll() {
        return this.userService.getAllUsers();
    }
}
