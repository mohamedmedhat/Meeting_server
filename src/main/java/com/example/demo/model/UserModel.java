package com.example.demo.model;

import jakarta.persistence.*;

@Entity(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    public String name;

    @Column
    public String email;

    @Column
    public String password;
}
