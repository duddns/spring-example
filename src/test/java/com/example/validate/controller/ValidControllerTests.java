package com.example.validate.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "unit-test")
@Transactional
public class ValidControllerTests {

    @Autowired
    private MockMvc mockMvc;
    
    
    @Test
    public void test_DTO_검증_실패() throws Exception {
        // given
        
        // when
        
        // then
        mockMvc.perform(post("/api/valid")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code").value(400))
        .andExpect(jsonPath("$.message").value("message must not be empty"))
        ;
    }
    
    @Test
    public void test_DTO_검증_성공() throws Exception {
        // given
        
        // when
        
        // then
        mockMvc.perform(post("/api/valid")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"message\":\"hello\"}"))
        .andDo(print())
        .andExpect(status().isOk())
        ;
    }
}
