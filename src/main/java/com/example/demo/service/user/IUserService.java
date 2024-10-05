package com.example.demo.service.user;

import com.example.demo.dto.LoginInputRequestDto;
import com.example.demo.dto.RegisterInputRequestDto;
import com.example.demo.model.UserModel;
import com.example.demo.responses.LoginResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IUserService {
    CompletableFuture<UserModel> signUp(RegisterInputRequestDto userData);
    CompletableFuture<LoginResponse> login(LoginInputRequestDto userData);
    CompletableFuture<UserModel> updateUser(Long id, RegisterInputRequestDto userData);
    CompletableFuture<UserModel> getUserById(Long id);
    CompletableFuture<List<UserModel> >getAllUsers();
    CompletableFuture<Boolean> deleteUser(Long id);
}
