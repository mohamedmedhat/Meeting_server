package com.example.demo.service.user;

import com.example.demo.dto.LoginInputRequestDto;
import com.example.demo.dto.RegisterInputRequestDto;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.responses.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public CompletableFuture<UserModel> signUp(RegisterInputRequestDto userData) {
        return null;
    }

    @Override
    public CompletableFuture<LoginResponse> login(LoginInputRequestDto userData) {
        return null;
    }

    @Override
    public CompletableFuture<UserModel> updateUser(Long id, RegisterInputRequestDto userData) {
        return null;
    }

    @Override
    public CompletableFuture<UserModel> getUserById(Long id) {
        return null;
    }

    @Override
    public CompletableFuture<List<UserModel>> getAllUsers() {
        return CompletableFuture.completedFuture(this.userRepository.findAll());
    }

    @Override
    public CompletableFuture<Boolean> deleteUser(Long id) {
        return null;
    }
}
