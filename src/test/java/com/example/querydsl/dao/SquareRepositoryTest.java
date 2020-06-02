package com.example.querydsl.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.querydsl.model.Square;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(value = "unit-test")
public class SquareRepositoryTest {

    @Autowired
    private SquareRepository squareRepository;
    
    
    @Test
    public void test_SaveAndLoad() {
        // given
        
        // when
        
        // then
        squareRepository.save(Square.builder()
                .username("username")
                .password("password")
                .build());
        
        List<Square> list = squareRepository.findAll();
        
        Square item = list.get(0);
        
        assertThat(item.getUsername()).isEqualTo("username");
        assertThat(item.getPassword()).isEqualTo("password");
    }
    
    @Test
    public void test_search() {
        // given
        squareRepository.save(Square.builder()
                .username("user1")
                .password("pass1")
                .build());
        
        // when
        Square item = squareRepository.search("user1");
        
        // then
        assertThat(item.getUsername()).isEqualTo("user1");
        assertThat(item.getPassword()).isEqualTo("pass1");
    }
}
