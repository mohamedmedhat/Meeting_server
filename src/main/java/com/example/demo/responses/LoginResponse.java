package com.example.demo.responses;

import com.example.demo.model.UserModel;

public class LoginResponse {
    private UserModel user;
    private String token;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
