package com.example.querydsl.dao;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import com.example.querydsl.model.QUser;
import com.example.querydsl.model.User;

@Transactional(readOnly = true)
public class UserCustomRepositoryImpl extends QuerydslRepositorySupport implements UserCustomRepository {

    public UserCustomRepositoryImpl() {
        super(User.class);
    }
    
    
    @Override
    public User search(String keyword) {
        QUser user = QUser.user;
        return from(user)
                .where(user.username.eq(keyword))
                .fetchFirst();
    }
}
