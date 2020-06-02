package com.example.querydsl.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.example.model.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "squares")
public class Square extends BaseEntity {

    @Column(name = "username", unique = true)
    private String username;
    
    @Column(name = "password")
    private String password;
    
    
    @Builder
    public Square(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
