package com.example.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.model.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "security_users")
public class User extends BaseEntity {

    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    
    @Builder
    public User( String username, String password) {
        this.username = username;
        this.password = password;
    }
}
