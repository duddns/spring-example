package com.example.querydsl.dao;

import com.example.querydsl.model.User;

public interface UserCustomRepository {

    public User search(String keyword);
}
