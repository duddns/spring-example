package com.example.querydsl.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.example.model.BaseEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity(name = "users")
public class User extends BaseEntity {

    @Column(name = "username", unique = true)
    private String username;
    
    @Column(name = "password")
    private String password;
    
    
    @Builder
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
