package com.example.querydsl.dao;

import com.example.querydsl.model.Square;

public interface SquareCustomRepository {

    Square search(String keyword);
}
