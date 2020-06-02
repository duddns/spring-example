package com.example.querydsl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.querydsl.model.Square;

@Repository
public interface SquareRepository extends JpaRepository<Square, Long>, SquareCustomRepository {

}
