package com.example.querydsl.dao;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import com.example.querydsl.model.Square;
import com.example.querydsl.model.QSquare;

@Transactional(readOnly = true)
public class SquareCustomRepositoryImpl extends QuerydslRepositorySupport implements SquareCustomRepository {

    public SquareCustomRepositoryImpl() {
        super(Square.class);
    }
    
    
    @Override
    public Square search(String keyword) {
        QSquare square = QSquare.square;
        return from(square)
                .where(square.username.eq(keyword))
                .fetchFirst();
    }
}
